package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.data.exception.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the parser of user-given commands.
 */
public class Parser {

    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<taskIndex>-?\\d+)");
    public static final Pattern TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)");
    public static final Pattern DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /by (?<by>[^/]+)");
    public static final Pattern EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /from (?<from>[^/]+)"
                    + " /to (?<to>[^/]+)");

    /**
     * Returns a command from the given user input. If no such command matches
     * the user input, or there was an error with the command arguments,
     * throws a {@code DukeException}.
     *
     * @param fullCommand The given user input.
     * @return The command represented by the user input.
     * @throws DukeException If no such command exists or there was an error with
     *                       the command arguments.
     */
    public Command parse(String fullCommand) throws DukeException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(fullCommand.trim());
        if (!matcher.matches()) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
            case AddTodoCommand.COMMAND_WORD:
                return prepareTodo(arguments);
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareDeadline(arguments);
            case AddEventCommand.COMMAND_WORD:
                return prepareEvent(arguments);
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();
            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments, true);
            case UnmarkCommand.COMMAND_WORD:
                return prepareMark(arguments, false);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns an {@code AddTodoCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddTodoCommand} with the given arguments.
     * @throws DukeException If the arguments were invalid.
     */
    private Command prepareTodo(String arguments) throws DukeException {
        final Matcher matcher = TODO_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The description of a todo must be specified.");
        }

        return new AddTodoCommand(matcher.group("description"));
    }

    /**
     * Returns an {@code AddDeadlineCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddDeadlineCommand} with the given arguments.
     * @throws DukeException If the arguments were invalid.
     */
    private Command prepareDeadline(String arguments) throws DukeException {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The description and the /by field must be specified.");
        }

        return new AddDeadlineCommand(matcher.group("description"), matcher.group("by"));
    }

    /**
     * Returns an {@code AddEventCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddEventCommand} with the given arguments.
     * @throws DukeException If the arguments were invalid.
     */
    private Command prepareEvent(String arguments) throws DukeException {
        final Matcher matcher = EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The description and the /from and /to fields must be specified.");
        }

        return new AddEventCommand(
                matcher.group("description"),
                matcher.group("from"),
                matcher.group("to"));
    }

    /**
     * Returns an {@code MarkCommand} or {@code UnmarkCommand} from the given arguments.
     *
     * @param arguments The user arguments to the command.
     * @param isMarked True if the command is meant to mark the task, false otherwise.
     * @return An instance of {@code MarkCommand} or {@code UnmarkCommand} with the given arguments.
     * @throws DukeException If the task was not specified or invalid.
     */
    private Command prepareMark(String arguments, boolean isMarked) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The task must be specified.");
        }

        if (isMarked) {
            return new MarkCommand(matcher.group("taskIndex"));
        } else {
            return new UnmarkCommand(matcher.group("taskIndex"));
        }
    }

    /**
     * Returns an {@code DeleteCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code DeleteCommand} with the given arguments.
     * @throws DukeException If the task was not specified or invalid.
     */
    private Command prepareDelete(String arguments) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The task must be specified.");
        }

        return new DeleteCommand(matcher.group("taskIndex"));
    }

}

