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

    private Command prepareTodo(String arguments) throws DukeException {
        final Matcher matcher = TODO_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        return new AddTodoCommand(matcher.group("description"));
    }

    private Command prepareDeadline(String arguments) throws DukeException {
        final Matcher matcher = DEADLINE_DATA_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The description and the /by field must be specified.");
        }

        return new AddDeadlineCommand(matcher.group("description"), matcher.group("by"));
    }

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

    private Command prepareDelete(String arguments) throws DukeException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new DukeException("The task must be specified.");
        }

        return new DeleteCommand(matcher.group("taskIndex"));
    }

}

