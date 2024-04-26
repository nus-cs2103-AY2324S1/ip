package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * Parser class handles the interpretation of user input into commands that can be executed.
 */
public class Parser {
    /** A List containing possible formats of date and time */
    private static final List<String> DATE_STRING_FORMATS = Arrays.asList(
            "yyyy-MM-dd HH:mm",
            "dd/MM/yyyy HH:mm",
            "MM-dd-yyyy HH:mm"
            // Add other formats here
    );

    /** Required date and time format */
    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Parses the user input and returns a Command object
     * representing the user's intended action.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object representing the action to be taken.
     */


    public static Command parse(String userInput) {
        assert userInput != null && !userInput.trim().isEmpty() : "Input cannot be empty."; // Check input is not empty.
        // Extracts the first word (the command word) from the input
        String commandWord = userInput.split(" ")[0].toLowerCase();

        switch (commandWord) {
        case "bye":
            return new ByeCommand();
            // Fallthrough
        case "list":
            return new ListCommand();
            // Fallthrough
        case "todo":
            return Parser.parseToDo(userInput);
            // Fallthrough
        case "deadline":
            return Parser.parseDeadline(userInput);
            // Fallthrough
        case "event":
            return Parser.parseEvent(userInput);

            // Fallthrough
        case "delete":
            return Parser.parseDelete(userInput);
            // Fallthrough
        case "mark":
            return Parser.parseMark(userInput);
            // Fallthrough
        case "unmark":
            return Parser.parseUnmark(userInput);
        case "find":
            return Parser.parseFind(userInput);
            // Fallthrough
        case "update":
            return Parser.parseUpdate(userInput);
            //Fallthrough
        default:
            return new ErrorCommand("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses the user input and returns an update command object.
     *
     * @param userInput The full input string entered by the user.
     * @return An updateCommand object that updates the specified field with the new Value.
     */
    public static Command parseUpdate(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            String field = userInput.split(" ", 4)[2];
            String newValue = userInput.split(" ", 4)[3];
            return new UpdateCommand(taskNumber, field, newValue);
        } catch (IllegalArgumentException e) {
            return new ErrorCommand("☹ OOPS!!! The format of your update is invalid.");
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("☹ OOPS!!! There are wrong number of fields given");
        }
    }

    /**
     * Changes the format of a date and time input string into a the  DEFAULT_DATE_TIME_FORMAT.
     *
     * @param dateTimeInput The date and time string to be formatted.
     * @return A string representing the date and time in a standard format.
     * @throws DateTimeParseException Exception thrown if the input does not match any of the expected formats.
     */
    public static String changeDateFormat(String dateTimeInput) throws DateTimeParseException {
        String dateTimeString = "";
        // Try parsing with different formats
        for (String formatString : DATE_STRING_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeInput, formatter);
                dateTimeString = dateTime.format(DEFAULT_DATE_TIME_FORMAT);
                break; // Stop at the first successful parse
            } catch (DateTimeParseException e) {
                // Ignore the exception and try the next format
            }
        }

        // if the input fits none of the format, throw DateTimeParseException
        if (dateTimeString.isEmpty()) {
            throw new DateTimeParseException(
                    "Invalid date/time format", dateTimeInput, 0);
        }

        return dateTimeString;
    }

    /**
     * Checks the sequence of start and due dates to ensure the start date is before the due date.
     *
     * @param startDateString The start date in string format.
     * @param dueDateString The due date in string format.
     * @throws IllegalArgumentException Exception thrown if the start date is equal to or after the due date.
     */
    public static void checkDateSequence(String startDateString, String dueDateString) throws IllegalArgumentException {
        LocalDateTime startDateTime = LocalDateTime.parse(startDateString, DEFAULT_DATE_TIME_FORMAT);
        LocalDateTime dueDateTime = LocalDateTime.parse(dueDateString, DEFAULT_DATE_TIME_FORMAT);
        boolean isStartAfterDue = startDateTime.isAfter(dueDateTime);
        boolean isStartEqualDue = startDateTime.isEqual(dueDateTime);
        if (isStartEqualDue || isStartAfterDue) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses the user input to create a new ToDo task.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to add a new ToDo task.
     */
    public static Command parseToDo(String userInput) {
        try {
            String taskDescription = userInput.split(" ", 2)[1];
            ToDo task = new ToDo(taskDescription);
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Parses the user input to create a new Deadline task.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to add a new Deadline task.
     */
    public static Command parseDeadline(String userInput) {
        try {
            String taskDescription = userInput.split(" /by ", 2)[0].split(" ", 2)[1];
            String dateInput = userInput.split(" /by ", 2)[1];
            String dueDate = Parser.changeDateFormat(dateInput);
            Deadline task = new Deadline(taskDescription, dueDate);
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (userInput.split(" ").length == 1) {
                return new ErrorCommand(
                        "☹ OOPS!!! The description of a deadline cannot be empty");
            } else {
                return new ErrorCommand(
                        "☹ OOPS!!! The description of a deadline "
                                + "does not have \"/by\" specified");
            }
        } catch (DateTimeParseException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The format of deadline is wrong, "
                            + "please write it in the correct format");
        }
    }

    /**
     * Parses the user input to create a new Event task.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to add a new Event task.
     */
    public static Command parseEvent(String userInput) {
        try {
            String taskName = userInput.split(" /from | /to ", 3)[0].split(" ", 2)[1];
            String startDateInput = userInput.split(" /from | /to ", 3)[1];
            String dueDateInput = userInput.split(" /from | /to ", 3)[2];
            String startDateString = Parser.changeDateFormat(startDateInput);
            String dueDateString = Parser.changeDateFormat(dueDateInput);
            Parser.checkDateSequence(startDateString, dueDateString);
            Event task = new Event(taskName, startDateString, dueDateString);
            return new AddCommand(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            if (userInput.split(" ").length == 1) {
                return new ErrorCommand(
                        "☹ OOPS!!! The description of a event cannot be empty");
            } else {
                return new ErrorCommand(
                        "☹ OOPS!!! The format of starting date or due date is wrong,"
                                + " please write it in the format correct format");
            }
        } catch (DateTimeParseException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The format of starting date or due date is wrong,"
                            + " please write it in the format correct format");
        } catch (IllegalArgumentException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The starting date "
                            + "cannot be later than the due date.");
        }
    }

    /**
     * Parses the user input to create a command to delete a task.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to delete a task based on the task number.
     */
    public static Command parseDelete(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The task number you entered "
                            + "for deleting is not in the list.");
        } catch (NumberFormatException e) {
            return new ErrorCommand("☹ OOPS!!! The task number you entered "
                    + "for deleting is invalid. Please enter a number.");
        }
    }

    /**
     * Parses the user input to create a command to mark a task as done.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to mark a task as done based on the task number.
     */
    public static Command parseMark(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            return new MarkCommand(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The task number you "
                            + "entered for marking is not in the list.");
        }
    }

    /**
     * Parses the user input to create a command to mark a task as undone.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to mark a task as undone based on the task number.
     */
    public static Command parseUnmark(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            return new UnmarkCommand(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand(
                    "☹ OOPS!!! The task number you "
                            + "entered for marking is not in the list.");
        }
    }

    /**
     * Parses the user input to create a command to find tasks that match a keyword.
     *
     * @param userInput The full input string entered by the user.
     * @return A Command object to find tasks that contain the keyword.
     */
    public static Command parseFind(String userInput) {
        try {
            String keyWord = userInput.split(" ", 2)[1];
            return new FindCommand(keyWord);
        } catch (IndexOutOfBoundsException e) {
            return new ErrorCommand("☹ OOPS!!! You did not specify "
                    + "your key word you are finding for.");

        }
    }
}
