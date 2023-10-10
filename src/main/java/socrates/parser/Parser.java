package socrates.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import socrates.command.AddDeadlineCommand;
import socrates.command.AddEventCommand;
import socrates.command.AddTodoCommand;
import socrates.command.ClearCommand;
import socrates.command.Command;
import socrates.command.DeleteCommand;
import socrates.command.ExitCommand;
import socrates.command.FindCommand;
import socrates.command.HelpCommand;
import socrates.command.ListCommand;
import socrates.command.MarkCommand;
import socrates.command.UnmarkCommand;
import socrates.data.exception.InvalidCommandException;
import socrates.data.exception.InvalidKeywordException;
import socrates.data.exception.InvalidTaskArgumentException;
import socrates.data.exception.InvalidTaskIndexException;
import socrates.data.exception.SocratesException;

/**
 * Represents the parser of user-given commands.
 */
public class Parser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<taskIndex>-?\\d+)");
    private static final Pattern TODO_DATA_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)");
    private static final Pattern DEADLINE_DATA_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /by (?<by>[^/]+)");
    private static final Pattern EVENT_DATA_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /from (?<from>[^/]+)"
                    + " /to (?<to>[^/]+)");
    private static final Pattern KEYWORD_DATA_ARGS_FORMAT = Pattern.compile("(?<keyword>\\w+)");

    /**
     * Returns a command from the given user input. If no such command matches
     * the user input, or there was an error with the command arguments,
     * throws a {@code SocratesException}.
     *
     * @param fullCommand The given user input.
     * @return The command represented by the user input.
     * @throws SocratesException If no such command exists or there was an error with
     *                       the command arguments.
     */
    public static Command parse(String fullCommand) throws SocratesException {
        //@author samuelim01-reused
        // Reused from Addressbook Level 2 with minor modifications.
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(fullCommand.trim());
        if (!matcher.matches()) {
            throw new SocratesException("I'm sorry, but I don't know what that means :-(");
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
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            throw new InvalidCommandException();
        }
        //@@author
    }

    /**
     * Returns an {@code AddTodoCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddTodoCommand} with the given arguments.
     * @throws InvalidTaskArgumentException If the arguments were invalid.
     */
    private static Command prepareTodo(String arguments) throws SocratesException {
        final Matcher matcher = TODO_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskArgumentException("The description of a todo must be specified.");
        }

        return new AddTodoCommand(matcher.group("description"));
    }

    /**
     * Returns an {@code AddDeadlineCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddDeadlineCommand} with the given arguments.
     * @throws InvalidTaskArgumentException If the arguments were invalid.
     */
    private static Command prepareDeadline(String arguments) throws SocratesException {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskArgumentException("The description and the /by field must be specified.");
        }

        return new AddDeadlineCommand(matcher.group("description"), matcher.group("by"));
    }

    /**
     * Returns an {@code AddEventCommand} from the given arguments.
     * @param arguments The user arguments to the command.
     * @return An instance of {@code AddEventCommand} with the given arguments.
     * @throws InvalidTaskArgumentException If the arguments were invalid.
     */
    private static Command prepareEvent(String arguments) throws SocratesException {
        final Matcher matcher = EVENT_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskArgumentException("The description and the /from and /to fields must be specified.");
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
     * @throws InvalidTaskIndexException If the task was not specified or invalid.
     */
    private static Command prepareMark(String arguments, boolean isMarked) throws SocratesException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskIndexException("The task must be specified.");
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
     * @throws SocratesException If the task was not specified or invalid.
     */
    private static Command prepareDelete(String arguments) throws SocratesException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidTaskIndexException("The task must be specified.");
        }

        return new DeleteCommand(matcher.group("taskIndex"));
    }

    /**
     * Returns a {@code FindCommand} from the given arguments.
     *
     * @param arguments  The user arguments to the command.
     * @return An instance of {@code FindCommand} with the given arguments.
     * @throws InvalidKeywordException If keyword was not specified or more than one.
     */
    private static Command prepareFind(String arguments) throws InvalidKeywordException {
        final Matcher matcher = KEYWORD_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new InvalidKeywordException();
        }

        return new FindCommand(matcher.group("keyword"));
    }

}

