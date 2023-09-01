package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a parser that parses user input to create appropriate Command objects.
 */
public class Parser {

    /**
     * Enum to identify types of commands.
     */
    public enum CommandType {
        LIST, DELETE, MARK, UNMARK, ADD, UNKNOWN, BYE, FIND
    }

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param userInput The string input by the user.
     * @return A Command object representing the user's desired action.
     * @throws InvalidCommandException If the user input is invalid.
     */
    public static Command parse(String userInput) throws InvalidCommandException, EmptyDescriptionException {
        CommandType commandType = parseCommand(userInput);
        switch (commandType) {
        case LIST:
            return new ListCommand();

        case DELETE:
            // Extract the index to delete
            int deleteIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(deleteIndex);

        case MARK:
            // Extract the index to mark
            int markIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new MarkCommand(markIndex);

        case UNMARK:
            // Extract the index to unmark
            int unmarkIndex = Integer.parseInt(userInput.split(" ")[1]);
            return new UnmarkCommand(unmarkIndex);

        case ADD:
            if (userInput.startsWith("todo")) {
                if (userInput.equals("todo")) {
                    throw new EmptyDescriptionException();
                }
                String taskName = userInput.substring(5).trim();
                return new AddCommand(new Todo(taskName));
            } else {
                String trim = userInput.substring(userInput.indexOf(' ') + 1).trim();
                if (userInput.startsWith("deadline")) {
                    int deadlineIndex = trim.indexOf("/by");
                    String taskDeadline = trim.substring(deadlineIndex + 4).trim();
                    String taskName = trim.substring(0, deadlineIndex).trim();
                    return new AddCommand(new Deadline(taskName, taskDeadline));
                } else if (userInput.startsWith("event")) {
                    int fromIndex = trim.indexOf("/from");
                    int toIndex = trim.indexOf("/to");
                    String taskFrom = trim.substring(fromIndex + 6, toIndex - 1).trim();
                    String taskTo = trim.substring(toIndex + 4).trim();
                    String taskName = trim.substring(0, fromIndex).trim();
                    return new AddCommand(new Event(taskName, taskFrom, taskTo));
                }
            }
            break;

        case FIND:
            String keyword = userInput.substring(5).trim();
            return new FindCommand(keyword);
        case BYE:
            return new ExitCommand();

        default:
            throw new InvalidCommandException();
        }

        return null;
    }

    /**
     * Determines the type of command based on the given user input.
     *
     * @param userInput The string input by the user.
     * @return CommandType indicating the type of command.
     */
    private static CommandType parseCommand(String userInput) {
        if (userInput.equals("bye")) {
            return CommandType.BYE;
        } else if (userInput.startsWith("list")) {
            return CommandType.LIST;
        } else if (userInput.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (userInput.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith("mark")) {
            return CommandType.MARK;
        } else if (userInput.startsWith("find")) {
            return CommandType.FIND;
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            return CommandType.ADD;
        } else {
            return CommandType.UNKNOWN;
        }
    }
}
