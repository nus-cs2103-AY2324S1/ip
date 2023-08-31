package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            // Add event task into task list
            if (!userInput.matches("event .*/from .* /to .*")) {
                throw new DukeException("OOPS!!! The format of an event task is " +
                        "\"event TASK_DESCRIPTION /from START /to END\"");
            }
//            String[] taskParts = splitCommand[1].split("/");
            String description = splitCommand[1].split("/from")[0];
            String[] dateAndTime = splitCommand[1].split("/from")[1].split("/to");
            return new AddCommand(new Event(description, dateAndTime[0], dateAndTime[1]));
        case "deadline":
            // Add deadline task into task list
            return parseDeadlineCommand(splitCommand[1]);
        case "todo":
            // Add to-do task into task list
            if (!userInput.matches("todo .*")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new ToDo(splitCommand[1]));
        case "mark":
        case "unmark":
        case "delete":
            if (!userInput.matches(".* \\d+")) {
                String errorMessage = String.format(
                        "OOPS!!! The format of this command is \"%s TASK_NUMBER\". "
                                + "Task number must exist in the task list.", splitCommand[0]);
                throw new DukeException(errorMessage);
            }

            int taskNumber = Integer.parseInt(splitCommand[1]);
            switch (splitCommand[0]) {
            case "mark":
                return new MarkCommand(taskNumber);
            case "unmark":
                return new UnmarkCommand(taskNumber);
            case "delete":
                return new DeleteCommand(taskNumber);
            }
        case "find":
            if (!userInput.matches("find .*")) {
                throw new DukeException("OOPS!!! The description of a find command cannot be empty.");
            }
            return new FindCommand(splitCommand[1]);
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
        String errorMessage = "OOPS!!! The format of a deadline task is " +
                "\"deadline TASK_DESCRIPTION /by DD/MM/YYYY 24H_TIME\"";

        if (!stringCommand.matches(".*/by \\d{1,2}/\\d{1,2}/\\d{4} \\d{4}")) {
            throw new DukeException(errorMessage);
        }

        String[] taskParts = stringCommand.split(" /by ");
        if (taskParts.length != 2) {
            throw new DukeException(errorMessage);
        }

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
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    /**
     * Parses a time string and returns a LocalTime object.
     *
     * @param time The time of the deadline for the task.
     * @return A LocalTime object of the deadline task.
     */
    public static LocalTime parseTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }
}