package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.error.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parses user input commands and creates corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the full user input command and returns the corresponding Command object.
     *
     * @param fullCommand The full user input command.
     * @return The corresponding Command object.
     * @throws DukeException If there's an issue parsing the command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] tokens = fullCommand.split(" ", 2);
        String rootCmd = tokens[0];
        switch (rootCmd) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();

        case "delete": {
            if (tokens.length == 1) {
                throwException("The task index of a task cannot be empty", "delete <task index>");
            }
            int index = Validate.validateNumber(tokens[1]);
            return new DeleteCommand(index);
        }
        case "mark": {
            if (tokens.length == 1) {
                throwException("The task index of a task cannot be empty", "mark <task index>");
            }
            int index = Validate.validateNumber(tokens[1]);
            return new MarkCommand(index);
        }
        case "unmark": {
            if (tokens.length == 1) {
                throwException("The task index of a task cannot be empty", "unmark <task index>");
            }
            int index = Validate.validateNumber(tokens[1]);
            return new UnmarkCommand(index);

        }
        case "find": {
            if (tokens.length == 1) {
                throwException("The search keyword cannot be empty", "find <search keyword>");
            }
            return new FindCommand(tokens[1]);
        }
        case "todo":
            if (fullCommand.length() < 5) {
                throwException("The description of a todo cannot be empty", "todo <description>");
            }
            String todoText = fullCommand.substring(5);
            if (todoText.trim().equals("")) {
                throwException("The description of a todo cannot be empty", "todo <description>");
            }
            return new AddCommand(new Todo(todoText));
        case "deadline": {
            String usageText = "deadline <description> /by <due date/time>";
            if (tokens.length == 1) {
                throwException("The description and due date of a deadline cannot be empty.", usageText);
            }

            String deadlineText = tokens[1];
            String[] parts = deadlineText.split("/by", 2);

            if (parts.length == 0) {
                throwException("The description and due date of a deadline cannot be empty.", usageText);
            } else if (parts.length == 1) {
                throwException("The due date of a deadline cannot be empty.", usageText);
            }

            String description = parts[0].trim();
            String date = parts[1].trim();
            if (description.trim().equals("")) {
                throwException("The description of a deadline cannot be empty.", usageText);
            }
            if (date.trim().equals("")) {
                throwException("The due date of a deadline cannot be empty.", usageText);
            }
            return new AddCommand(new Deadline(description, Validate.validateLocalDateTime(date)));
        }
        case "event": {
            String usageText = "event <description> /from <start date/time> /to <end date/time>";
            if (tokens.length == 1) {
                throwException("The description, start date/time and end date/time of an event cannot be empty.",
                        usageText);
            }

            String eventText = tokens[1];
            String[] eventParts = eventText.split("/from", 2);

            if (eventParts.length == 0) {
                throwException("The description, start date/time and end date/time of an event cannot be empty.",
                        usageText);
            } else if (eventParts.length == 1) {
                throwException("The start date/time and end date/time of an event cannot be empty.", usageText);
            }

            String description = eventParts[0].trim();
            String[] fromToParts = eventParts[1].split("/to", 2);

            if (fromToParts.length == 0) {
                throwException("The start date/time and end date/time of an event cannot be empty.", usageText);
            } else if (fromToParts.length == 1) {
                throwException("The end date/time of an event cannot be empty.", usageText);
            }

            String fromDate = fromToParts[0].trim();
            String toTime = fromToParts[1].trim();
            if (description.trim().equals("")) {
                throwException("The description of an event cannot be empty.", usageText);
            }
            if (fromDate.trim().equals("")) {
                throwException("The start date/time of an event cannot be empty.", usageText);
            }
            if (toTime.trim().equals("")) {
                throwException("The end date/time of an event cannot be empty.", usageText);
            }
            return new AddCommand(new Event(description,
                    Validate.validateLocalDateTime(fromDate),
                    Validate.validateLocalDateTime(toTime))
            );
        }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Throws a DukeException with a formatted error message including usage information.
     *
     * @param message   The error message to display.
     * @param usageText The usage information for the command.
     * @throws DukeException The exception with the formatted error message.
     */
    private static void throwException(String message, String usageText) throws DukeException {
        throw new DukeException(String.format("%s\n\n\tUsage: %s", message, usageText));
    }
}
