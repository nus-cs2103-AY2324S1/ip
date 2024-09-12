package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidFormatException;
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
        LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT, UNKNOWN, BYE, FIND, SORT
    }
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String FIND_COMMAND = "find";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DELETE_COMMAND = "delete";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String SORT_COMMAND = "sort";
    private static final String BY_PREFIX = "/by";
    private static final String FROM_PREFIX = "/from";
    private static final String TO_PREFIX = "/to";
    private static final int LENGTH_BY_PREFIX = BY_PREFIX.length();
    private static final int LENGTH_FROM_PREFIX = FROM_PREFIX.length();
    private static final int LENGTH_TO_PREFIX = TO_PREFIX.length();
    private static final int LENGTH_TODO_COMMAND = TODO_COMMAND.length();
    private static final int LENGTH_DEADLINE_COMMAND = DEADLINE_COMMAND.length();
    private static final int LENGTH_EVENT_COMMAND = EVENT_COMMAND.length();
    private static final int LENGTH_FIND_COMMAND = FIND_COMMAND.length();

    /**
     * Parses the user input and returns the appropriate Command object.
     *
     * @param userInput The string input by the user.
     * @return A Command object representing the user's desired action.
     * @throws DukeException If the user input is invalid.
     */
    public static Command parse(String userInput) throws DukeException {
        userInput = userInput.trim();
        CommandType commandType = parseCommand(userInput.toLowerCase());

        String[] words = userInput.split(" ");

        switch (commandType) {
        case LIST:
            return new ListCommand();

        case DELETE:
            // Extract the index to delete
            int deleteIndex = parseIndex(words);
            return new DeleteCommand(deleteIndex);

        case MARK:
            // Extract the index to mark
            int markIndex = parseIndex(words);
            return new MarkCommand(markIndex);

        case UNMARK:
            // Extract the index to unmark
            int unmarkIndex = parseIndex(words);
            return new UnmarkCommand(unmarkIndex);

        case TODO:
            String todoName = userInput.substring(LENGTH_TODO_COMMAND).trim();
            if (todoName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            return new AddCommand(new Todo(todoName));

        case DEADLINE:
            int deadlineIndex = userInput.toLowerCase().indexOf(BY_PREFIX);
            if (deadlineIndex == -1) {
                throw new InvalidFormatException("The command is missing the /by prefix.");
            }
            String taskDeadline = userInput.substring(deadlineIndex + LENGTH_BY_PREFIX).trim();
            String deadlineName = userInput.substring(LENGTH_DEADLINE_COMMAND, deadlineIndex).trim();
            if (deadlineName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            try {
                return new AddCommand(new Deadline(deadlineName, taskDeadline));
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException();
            }

        case EVENT:
            int fromIndex = userInput.toLowerCase().indexOf(FROM_PREFIX);
            int toIndex = userInput.toLowerCase().indexOf(TO_PREFIX);
            if (fromIndex == -1 || toIndex == -1) {
                throw new InvalidFormatException("The command is missing the /from or /to prefix.");
            }
            String taskFrom = userInput.substring(fromIndex + LENGTH_FROM_PREFIX, toIndex - 1).trim();
            String taskTo = userInput.substring(toIndex + LENGTH_TO_PREFIX).trim();
            String eventName = userInput.substring(LENGTH_EVENT_COMMAND, fromIndex).trim();
            if (eventName.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            if (taskFrom.isBlank() || taskTo.isBlank()) {
                throw new InvalidDateFormatException();
            }
            try {
                return new AddCommand(new Event(eventName, taskFrom, taskTo));
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException();
            }

        case FIND:
            if (words.length < 2) {
                throw new InvalidFormatException("Please provide a valid keyword to search.");
            }
            String keyword = userInput.substring(LENGTH_FIND_COMMAND).trim();
            return new FindCommand(keyword);

        case SORT:
            return new SortCommand();

        case BYE:
            return new ExitCommand();

        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Determines the type of command based on the given user input.
     *
     * @param userInput The string input by the user.
     * @return CommandType indicating the type of command.
     */
    private static CommandType parseCommand(String userInput) {
        if (userInput.equals(BYE_COMMAND)) {
            return CommandType.BYE;
        } else if (userInput.equals(LIST_COMMAND)) {
            return CommandType.LIST;
        } else if (userInput.startsWith(DELETE_COMMAND)) {
            return CommandType.DELETE;
        } else if (userInput.startsWith(UNMARK_COMMAND)) {
            return CommandType.UNMARK;
        } else if (userInput.startsWith(MARK_COMMAND)) {
            return CommandType.MARK;
        } else if (userInput.startsWith(FIND_COMMAND)) {
            return CommandType.FIND;
        } else if (userInput.equals(SORT_COMMAND)) {
            return CommandType.SORT;
        } else if (userInput.startsWith(TODO_COMMAND)) {
            return CommandType.TODO;
        } else if (userInput.startsWith(DEADLINE_COMMAND)) {
            return CommandType.DEADLINE;
        } else if (userInput.startsWith(EVENT_COMMAND)) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    private static int parseIndex(String[] words) throws InvalidFormatException {
        if (words.length < 2) {
            throw new InvalidFormatException("Please provide a valid index.");
        }
        try {
            return Integer.parseInt(words[1]);
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("The index provided is not a valid number.");
        }
    }
}
