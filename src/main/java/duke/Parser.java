package duke;

import duke.command.*;
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
            int taskIndex2 = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(taskIndex2);
        case "unmark":
            int taskIndex3 = Integer.parseInt(input.substring(7)) - 1;
            return new UnmarkCommand(taskIndex3);
        case "todo":
            if (input.length() <= 5) {
            throw new DukeException("\t The description of a todo" +
             " cannot be empty.");
            }
            return new AddCommand(new Todo(input.substring(5), false));
        case "deadline":
        // Parse deadline command and create DeadlineTask object
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("\t The deadline description" +
                 " must include a /by date.");
            }
            String description = input.substring(9, byIndex).trim();
            if (description.isEmpty()) {
                throw new DukeException("\t The deadline description" +
                 " cannot be empty.");
            }
            String by = input.substring(byIndex + 3).trim();
            // Check if 'by' is in the format yyyy-MM-dd
            if (!isValidDateFormat(by)) {
                throw new DukeException("\t The deadline date must be in the format yyyy-MM-dd.");
            }
            return new AddCommand(new Deadline(description, false, by));
        case "event":
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 && toIndex == -1) {
                throw new DukeException("\t The event description" +
                 " must include both /from and /to dates.");
            }
            String description2 = input.substring(6, fromIndex).trim();
            if (description2.isEmpty()) {
                throw new DukeException("\t The event description cannot" +
                 " be empty.");
            }
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            if (!isValidDateFormat(from) || !isValidDateFormat(to)) {
                throw new DukeException("\t The event from and to date must be in the format yyyy-MM-dd.");
            }
            // Parse event command and create EventTask object
            return new AddCommand(new Event(description2, false, from, to));
        case "delete":
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(taskIndex);
        case "find":
            String keyword = input.substring(5).trim();
            return new FindCommand(keyword);
        default:
            throw new DukeException("\t I'm sorry, but I don't know what that means.");
        }
    }
    // Function to check if a string is in the format "yyyy-MM-dd"
    private static boolean isValidDateFormat(String date) {
        String dateFormatPattern = "\\d{4}-\\d{2}-\\d{2}";
        return date.matches(dateFormatPattern);
    }

    private static boolean isValidCommandType(String commandType) {
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
