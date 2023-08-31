package duke.parser;

import duke.commands.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents a parser to convert user input into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and converts it into an executable command.
     *
     * @param input The user input.
     * @return A Command object based on the parsed input.
     */
    public static Command parse(String input) {
        String[] split = input.split(" ", 2);
        Command c = null;

        switch (split[0]) {
            case "bye":
                c = validateExit(split);
                break;
            case "list":
                c = validateList(split);
                break;
            case "mark":
            case "unmark":
                c = validateMark(split);
                break;
            case "todo":
                c = validateTodo(split);
                break;
            case "deadline":
                c = validateDeadline(split);
                break;
            case "event":
                c = validateEvent(split);
                break;
            case "delete":
                c = validateDelete(split);
                break;
            case "find":
                c = validateFind(split);
                break;
            default:
                c = new IncorrectCommand("I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }

    /**
     * Validates and constructs a Todo command.
     *
     * @param split The user input split into parts.
     * @return A TodoCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateTodo(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }
        return new AddCommand(split[1]);
    }

    /**
     * Validates and constructs a Deadline command.
     *
     * @param split The user input split into parts.
     * @return A DeadlineCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateDeadline(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }
        if (!split[1].contains(" /by ")) {
            return new IncorrectCommand("Please indicate a deadline using /by");
        }

        String[] task = split[1].split(" /by ", 2);
        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand("Please enter a valid task and/or deadline.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return new AddCommand(task[0], LocalDateTime.parse(task[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please enter the date & time in DD/MM/YY HHMM format");
        }
    }

    /**
     * Validates and constructs an Event command.
     *
     * @param split The user input split into parts.
     * @return An EventCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateEvent(String[] split) {
        if (!split[1].contains(" /from ")) {
            return new IncorrectCommand("Please indicate a start datetime using /from.");
        }

        String[] task = split[1].split(" /from ", 2);

        if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }

        if (!task[1].contains(" /to ")) {
            return new IncorrectCommand("Please indicate an end datetime using /to.");
        }

        String[] to = task[1].split(" /to ", 2);

        if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
            return new IncorrectCommand("Please enter valid to & from dates");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(to[0], formatter);
            LocalDateTime till = LocalDateTime.parse(to[1], formatter);
            if (from.isAfter(till) || from.isEqual(till)) {
                return new IncorrectCommand("Please ensure that the start date is not equal to" +
                        " or later than the end date");
            }
            return new AddCommand(task[0], from, till);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Please enter the date & time in DD/MM/YY HHMM format");
        }
    }

    /**
     * Validates and constructs an Exit command.
     *
     * @param split The user input split into parts.
     * @return An ExitCommand object if input is valid, else an IncorrectCommand object.
     */

    private  static Command validateFind(String[] split) {
        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid find command.");
        }
        return new FindCommand(split[1]);
    }

    private static Command validateExit(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("The bye command should not have " +
                    "any additional words appended to it");
        }
        return new ExitCommand();
    }

    /**
     * Validates and constructs a List command.
     *
     * @param split The user input split into parts.
     * @return A ListCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateList(String[] split) {
        if (split.length != 1) {
            return new IncorrectCommand("The list command should not have any " +
                    "additional words appended to it");
        }
        return new ListCommand();
    }

    /**
     * Validates and constructs a Mark command.
     *
     * @param split The user input split into parts.
     * @return A MarkCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateMark(String[] split) {
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid mark command!");
        }
        if (!Character.isDigit(split[1].charAt(0))) {
            return new IncorrectCommand("The second argument must be a digit!");
        }
        int index = Integer.parseInt(split[1]);
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }
        return new MarkCommand(index, split[0]);
    }

    /**
     * Validates and constructs a Delete command.
     *
     * @param split The user input split into parts.
     * @return A DeleteCommand object if input is valid, else an IncorrectCommand object.
     */
    public static Command validateDelete(String[] split) {
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid delete command!");
        }
        if (!Character.isDigit(split[1].charAt(0))) {
            return new IncorrectCommand("The second argument must be a digit!");
        }
        int index = Integer.parseInt(split[1]);
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }
        return new DeleteCommand(index);
    }
}
