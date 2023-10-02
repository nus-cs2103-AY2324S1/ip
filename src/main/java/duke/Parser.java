package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses user input to create the appropriate command for Duke.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the user's request.
     * @throws DukeException If the input is invalid or contains errors.
     */
    public static Command parse(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0];
        assert isValidCommandType(commandType) : "Invalid command type";

        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
        case "unmark":
            return parseMarkOrUnmarkCommand(commandType, input);
        case "todo":
            return parseTodoCommand(input);
        case "deadline":
            return parseDeadlineCommand(input);
        case "event":
            return parseEventCommand(input);
        case "delete":
            return parseDeleteCommand(input);
        case "find":
            return parseFindCommand(input);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

    /**
     * Parses a mark or unmark command and returns the corresponding Duke command.
     *
     * @param commandType The type of command ('mark' or 'unmark').
     * @param input The user input to be parsed.
     * @return A Command object representing the mark/unmark command.
     * @throws DukeException If the input is invalid or contains errors.
     */
    private static Command parseMarkOrUnmarkCommand(String commandType, String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return commandType.equals("mark") ? new MarkCommand(taskIndex) : new UnmarkCommand(taskIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task index to mark/unmark.");
        }
    }

    /**
     * Parses a todo command and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the todo command.
     * @throws DukeException If the input is invalid or contains errors.
     */
    private static Command parseTodoCommand(String input) throws DukeException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new AddCommand(new Todo(description, false));
    }

    /**
     * Parses a deadline command and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the deadline command.
     * @throws DukeException If the input is invalid or contains errors.
     */
    private static Command parseDeadlineCommand(String input) throws DukeException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException("The deadline description must include a /by date.");
        }
        String description = input.substring(9, byIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The deadline description cannot be empty.");
        }
        String by = input.substring(byIndex + 3).trim();
        if (!isValidDateFormat(by)) {
            throw new DukeException("The deadline date must be in the format yyyy-MM-dd.");
        }
        return new AddCommand(new Deadline(description, false, by));
    }

    /**
     * Parses an event command and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the event command.
     * @throws DukeException If the input is invalid or contains errors.
     */
    private static Command parseEventCommand(String input) throws DukeException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DukeException("The event description must include both /from and /to dates.");
        }
        String description = input.substring(6, fromIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The event description cannot be empty.");
        }
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        if (!isValidDateFormat(from) || !isValidDateFormat(to)) {
            throw new DukeException("The event from and to date must be in the format yyyy-MM-dd.");
        }
        return new AddCommand(new Event(description, false, from, to));
    }

    /**
     * Parses a delete command and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the delete command.
     * @throws DukeException If the input is invalid or contains errors.
     */
    private static Command parseDeleteCommand(String input) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task index to delete.");
        }
    }

    /**
     * Parses a find command and returns the corresponding Duke command.
     *
     * @param input The user input to be parsed.
     * @return A Command object representing the find command.
     */
    private static Command parseFindCommand(String input) {
        String keyword = input.substring(5).trim();
        return new FindCommand(keyword);
    }

    /**
     * Checks if the given date string has the valid format "yyyy-MM-dd".
     *
     * @param date The date string to be validated.
     * @return True if the date string is in the valid format, false otherwise.
     */

    public static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "\\d{4}-\\d{2}-\\d{2}";
        return date.matches(dateFormatPattern);
    }

    /**
     * Checks if the provided command type is valid by comparing it to a list of known valid command types.
     *
     * @param commandType The command type to be validated.
     * @return True if the command type is valid, false otherwise.
     */
    public static boolean isValidCommandType(String commandType) {
        // Define a list of valid command types
        String[] validCommandTypes = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"};
        for (String validType : validCommandTypes) {
            if (validType.equals(commandType)) {
                return true;
            }
        }
        return false;
    }
}
