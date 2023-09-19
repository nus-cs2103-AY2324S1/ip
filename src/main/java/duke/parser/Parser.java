package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.FixedDurationTask;
import duke.task.ToDo;

/**
 * The Parser class deals with interpreting the user inputs
 * and generating corresponding commands if valid.
 */
public class Parser {
    /**
     * Returns a command based on the user input.
     * If the command is invalid, an exception is thrown.
     *
     * @param userInput The input provided by the user.
     * @return A Command object representing the user's intended action.
     * @throws DukeException If there is an error in the user input.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] splitCommand = userInput.trim().split(" ", 2);
        switch (splitCommand[0]) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "event":
            return parseEventCommand(userInput, splitCommand[1]);
        case "deadline":
            return parseDeadlineCommand(splitCommand[1]);
        case "todo":
            return parseTodoCommand(userInput, splitCommand);
        case "fixedDuration":
            return parseFixedDurationTask(splitCommand[1]);
        case "mark":
            return parseMarkCommand(userInput, splitCommand);
        case "unmark":
            return parseUnmarkCommand(userInput, splitCommand);
        case "delete":
            return parseDeleteCommand(userInput, splitCommand);
        case "find":
            return parseFindCommand(userInput, splitCommand[1]);
        default:
            throw new DukeException();
        }
    }

    /**
     * Returns a command for deadline tasks for valid user inputs.
     * Else, exception is thrown.
     *
     * @param stringCommand The deadline command provided by the user, excluding the task type.
     * @return A Command object with a deadline task.
     * @throws DateTimeParseException If the date or time inputs are invalid.
     * @throws DukeException If the deadline command has an invalid input format.
     */
    public static Command parseDeadlineCommand(String stringCommand)
            throws DateTimeParseException, DukeException {
        // Add deadline task into task list
        String errorMessage = "OOPS!!! The format of a deadline task is "
                + "\"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\"";

        if (!stringCommand.matches(".*/by \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            throw new DukeException(errorMessage);
        }

        String[] taskParts = stringCommand.split(" /by ");
        String[] dateAndTime = taskParts[1].split(" ");
        try {
            LocalDate date = parseDate(dateAndTime[0]);
            LocalTime time = parseTime(dateAndTime[1]);

            return new AddCommand(new Deadline(taskParts[0], date, time));
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! Invalid date or time format in deadline task.");
        }
    }

    /**
     * Parses a date string and returns a LocalDate object.
     *
     * @param date The date of the deadline for the task.
     * @return A LocalDate object of the deadline task.
     */
    public static LocalDate parseDate(String date) {
        assert date != null : "Parsed date cannot be null";
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    /**
     * Parses a time string and returns a LocalTime object.
     *
     * @param time The time of the deadline for the task.
     * @return A LocalTime object of the deadline task.
     */
    public static LocalTime parseTime(String time) {
        assert time != null : "Parsed time cannot be null";
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }

    /**
     * Parses a user input string to create a FixedDurationTask object.
     *
     * @param stringCommand The user input string to parse.
     * @return A Command object representing the parsed FixedDurationTask.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseFixedDurationTask(String stringCommand) throws DukeException {
        if (!stringCommand.matches(".*/needs .* .*")) {
            throw new DukeException("OOPS!!! The format of an event task is "
                    + "\"fixedDuration TASK_DESCRIPTION /needs DURATION UNITS(minutes OR hours)\"");
        }

        String[] taskParts = stringCommand.split("/needs ");
        String[] durationWithUnits = taskParts[1].split(" ");
        int duration = Integer.parseInt(durationWithUnits[0].trim());
        String units = durationWithUnits[1].toLowerCase().trim();

        if (!units.equals("minutes") && !units.equals("hours")) {
            throw new DukeException("OOPS!!! Invalid units. Use 'minutes' or 'hours' only.");
        }

        return new AddCommand(new FixedDurationTask(taskParts[0].trim(), duration, units));
    }

    /**
     * Parses a user input string to create an Event object.
     *
     * @param userInput The user input string to parse.
     * @param splitCommand The user input string to parse.
     * @return A Command object representing the parsed Event.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseEventCommand(String userInput, String splitCommand) throws DukeException {
        // Add event task into task list
        if (!userInput.matches("event .*/from .* /to .*")) {
            throw new DukeException("OOPS!!! The format of an event task is "
                    + "\"event TASK_DESCRIPTION /from START /to END\"");
        }

        String description = splitCommand.split("/from")[0].trim();
        String[] dateAndTime = splitCommand.split("/from")[1].split("/to");
        return new AddCommand(new Event(description, dateAndTime[0], dateAndTime[1]));
    }

    /**
     * Parses a user input string to create a ToDo object.
     *
     * @param userInput The user input string to parse.
     * @param stringCommand The user input string to parse.
     * @return A Command object representing the parsed ToDo.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseTodoCommand(String userInput, String[] stringCommand) throws DukeException {
        // Add to-do task into task list
        if (!userInput.matches("todo .*")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new AddCommand(new ToDo(stringCommand[1]));
    }

    /**
     * Parses a user input string to create a DeleteCommand object.
     *
     * @param userInput The user input string to parse.
     * @param splitCommand The task number to delete.
     * @return A DeleteCommand object representing the parsed delete command.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseDeleteCommand(String userInput, String[] splitCommand) throws DukeException {
        if (!userInput.matches(".* \\d+")) {
            String errorMessage = "OOPS!!! The format of delete command is \"delete TASK_NUMBER\". "
                            + "Task number must exist in the task list.";
            throw new DukeException(errorMessage);
        }

        return new DeleteCommand(Integer.parseInt(splitCommand[1]));
    }

    /**
     * Parses a user input string to create a MarkCommand object.
     *
     * @param userInput The user input string to parse.
     * @param splitCommand The task number to mark as done.
     * @return A MarkCommand object representing the parsed mark command.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseMarkCommand(String userInput, String[] splitCommand) throws DukeException {
        if (!userInput.matches(".* \\d+")) {
            String errorMessage = "OOPS!!! The format of mark command is \"mark TASK_NUMBER\". "
                    + "Task number must exist in the task list.";
            throw new DukeException(errorMessage);
        }
        return new MarkCommand(Integer.parseInt(splitCommand[1]));
    }

    /**
     * Parses a user input string to create an UnmarkCommand object.
     * @param userInput The user input string to parse.
     * @param splitCommand The task number to mark as undone.
     * @return An UnmarkCommand object representing the parsed unmark command.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseUnmarkCommand(String userInput, String[] splitCommand) throws DukeException {
        if (!userInput.matches(".* \\d+")) {
            String errorMessage = "OOPS!!! The format of unmark command is \"unmark TASK_NUMBER\". "
                    + "Task number must exist in the task list.";
            throw new DukeException(errorMessage);
        }
        return new UnmarkCommand(Integer.parseInt(splitCommand[1]));
    }

    /**
     * Parses a user input string to create a FindCommand object.
     * @param userInput The user input string to parse.
     * @param splitCommand The keyword to search for in the task list.
     * @return A FindCommand object representing the parsed find command.
     * @throws DukeException If there is an error in the user input or parsing.
     */
    public static Command parseFindCommand(String userInput, String splitCommand) throws DukeException {
        if (!userInput.matches("find .*")) {
            throw new DukeException("OOPS!!! The description of a find command cannot be empty.");
        }
        return new FindCommand(splitCommand);
    }
}
