package parser;

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkDoneCommand;
import commands.UnmarkCommand;
import commands.UnsureCommand;
import exceptions.FishronException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.ToDo;

/**
 * The Parser class is responsible for parsing user input and converting it into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input    The user input string.
     * @param taskList The TaskList to operate on.
     * @return A Command object representing the parsed command.
     * @throws FishronException If there is an issue with parsing or an invalid command.
     */
    public static Command parse(String input, TaskList taskList) throws FishronException {
        String[] commandAndArgs = input.trim().split(" ", 2);
        if (commandAndArgs.length < 1) {
            return new UnsureCommand();
        }
        String command = commandAndArgs[0].toLowerCase();
        String args = commandAndArgs.length > 1 ? commandAndArgs[1].trim() : "";
        isValidCommands(input, taskList);
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(args);
        case "mark":
            return new MarkDoneCommand(parseTaskIndex(args));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(args));
        case "delete":
            return new DeleteCommand(parseTaskIndex(args));
        case "todo":
            return new AddCommand(parseTodo(args));
        case "deadline":
            String[] deadlineParts = args.split("/by");
            if (deadlineParts.length == 2) {
                return new AddCommand(parseDeadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            }
            return new UnsureCommand();
        case "event":
            String[] eventParts = args.split("/from|/to");
            if (eventParts.length == 3) {
                return new AddCommand(parseEvent(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            }
            return new UnsureCommand();
        default:
            return new UnsureCommand();
        }
    }

    /**
     * Parses a string input and attempts to convert it into an integer representing a task index.
     *
     * @param input The input string to parse.
     * @return The parsed task index as an integer, or -1 if the input is not a valid integer.
     */
    private static int parseTaskIndex(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    /**
     * Validates whether the given command is valid and follows the required format.
     *
     * @param command  The command to validate.
     * @param taskList The TaskList to validate against.
     * @return True if the command is valid, otherwise throws an exception.
     * @throws FishronException If the command is invalid or in an incorrect format.
     */
    public static boolean isValidCommands(String command, TaskList taskList) throws FishronException {

        if (command.equals("list")) {
            return true;
        } else if (command.toLowerCase().startsWith("find") && command.split(command.substring(0, 4)).length <= 1) {
            throw new FishronException("☹ OOPS!!! Please provide a task to find.");
        } else if (command.toLowerCase().startsWith("mark") && command.split(command.substring(0, 4)).length <= 1) {
            throw new FishronException("☹ OOPS!!! Please provide a task to be marked.");
        } else if (command.toLowerCase().startsWith("unmark") && command.split(command.substring(0, 6)).length <= 1) {
            throw new FishronException("☹ OOPS!!! Please provide a task to be unmarked.");
        } else if (command.toLowerCase().startsWith("mark")) {
            validateMark(command, taskList);
        } else if (command.toLowerCase().startsWith("unmark")) {
            validateUnMark(command, taskList);
        } else if (command.toLowerCase().startsWith("delete")) {
            validateDelete(command, taskList);
        } else if (command.toLowerCase().startsWith("todo") && command.split(command.substring(0, 4)).length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (command.toLowerCase().startsWith("deadline") && command.split(command.substring(0, 8)).length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (command.toLowerCase().startsWith("deadline")) {
            validateDeadline(command, taskList);
        } else if (command.toLowerCase().startsWith("deadline")
                && (command.split("/by").length != 2 || command.split("/by")[1].trim().isEmpty())) {
            throw new FishronException("☹ OOPS!!! Please provide a valid deadline format.");
        } else if (command.toLowerCase().startsWith("event") && command.split(command.substring(0, 5)).length <= 1) {
            throw new FishronException("☹ OOPS!!! The description of an event cannot be empty.");
        } else if (command.toLowerCase().startsWith("event")) {
            validateEvent(command, taskList);
        } else if (command.toLowerCase().startsWith("event")
                && (command.split("/from|/to").length != 3 || command.split("/from|/to")[1].trim().isEmpty()
                || command.split("/from|/to")[2].trim().isEmpty())) {

            throw new FishronException("☹ OOPS!!! Please provide a valid event format.");
        }
        return true;
    }

    /**
     * Validates a "mark" command.
     *
     * @param command  The "mark" command to validate.
     * @param taskList The TaskList to validate against.
     * @throws FishronException If the "mark" command is invalid or in an incorrect format.
     */
    private static void validateMark(String command, TaskList taskList) throws FishronException {
        String taskIndexPart = command.split(command.substring(0, 4))[1].trim();
        try {
            Integer.parseInt(taskIndexPart);
        } catch (NumberFormatException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
        if (Integer.parseInt(taskIndexPart) < 1 || Integer.parseInt(taskIndexPart) > taskList.getSize()) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
    }

    /**
     * Validates an "unmark" command.
     *
     * @param command  The "unmark" command to validate.
     * @param taskList The TaskList to validate against.
     * @throws FishronException If the "unmark" command is invalid or in an incorrect format.
     */
    private static void validateUnMark(String command, TaskList taskList) throws FishronException {
        String taskIndexPart = command.split(command.substring(0, 6))[1].trim();
        try {
            Integer.parseInt(taskIndexPart);
        } catch (NumberFormatException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
        if (Integer.parseInt(taskIndexPart) < 1 || Integer.parseInt(taskIndexPart) > taskList.getSize()) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
    }

    /**
     * Validates a "delete" command.
     *
     * @param command  The "delete" command to validate.
     * @param taskList The TaskList to validate against.
     * @throws FishronException If the "delete" command is invalid or in an incorrect format.
     */
    private static void validateDelete(String command, TaskList taskList) throws FishronException {
        String taskIndexPart = command.split(command.substring(0, 6))[1].trim();
        try {
            Integer.parseInt(taskIndexPart);
        } catch (NumberFormatException e) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
        if (Integer.parseInt(taskIndexPart) < 1 || Integer.parseInt(taskIndexPart) > taskList.getSize()) {
            throw new FishronException("☹ OOPS!!! Please provide a valid task index.");
        }
    }

    /**
     * Validates a "deadline" command.
     *
     * @param command  The "deadline" command to validate.
     * @param taskList The TaskList to validate against.
     * @throws FishronException If the "deadline" command is invalid or in an incorrect format.
     */
    private static void validateDeadline(String command, TaskList taskList) throws FishronException {
        String[] parts = command.split("/by");
        try {
            String emptyDesc = parts[0].split(command.substring(0, 8))[1];
        } catch (Exception e) {
            throw new FishronException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Validates an "event" command.
     *
     * @param command  The "event" command to validate.
     * @param taskList The TaskList to validate against.
     * @throws FishronException If the "event" command is invalid or in an incorrect format.
     */
    private static void validateEvent(String command, TaskList taskList) throws FishronException {
        String[] parts = command.split("/from|/to");
        try {
            String emptyDesc = parts[0].split(command.substring(0, 5))[1];
        } catch (Exception e) {
            throw new FishronException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Parses a description string and creates a ToDo object.
     *
     * @param description The description of the ToDo task.
     * @return A ToDo object.
     */
    public static ToDo parseTodo(String description) {
        return new ToDo(description);
    }

    /**
     * Parses a description and a deadline string and creates a Deadline object.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in the format "dd-MM-yyyy HHmm".
     * @return A Deadline object.
     * @throws FishronException If the deadline format is invalid.
     */
    public static Deadline parseDeadline(String description, String by) throws FishronException {
        assert description != null;
        assert by != null;
        return new Deadline(description, by);
    }

    /**
     * Parses a description, a "from" string, and a "to" string and creates an Event object.
     *
     * @param description The description of the event task.
     * @param from        The starting date and time in the format "dd-MM-yyyy HHmm".
     * @param to          The ending date and time in the format "dd-MM-yyyy HHmm".
     * @return An Event object.
     * @throws FishronException If the date/time format is invalid.
     */
    public static Event parseEvent(String description, String from, String to) throws FishronException {
        assert description != null;
        assert from != null;
        assert to != null;
        return new Event(description, from, to);
    }
}
