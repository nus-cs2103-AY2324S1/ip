package duke.parser;

import duke.Deadline;
import duke.Event;
import duke.Todo;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static duke.common.Messages.MESSAGE_INVALID_COMMAND;

public class Parser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(?<command>\\S+)(?<arguments>.*)$");

    public Command parseCommand(String input) {
        Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND);
        }
        String command = matcher.group("command").trim();
        String args = matcher.group("arguments").trim();

        switch (command) {
            case TodoCommand.COMMAND_WORD:
                return parseTodo(args);
            case DeadlineCommand.COMMAND_WORD:
                return parseDeadline(args);
            case EventCommand.COMMAND_WORD:
                return parseEvent(args);
            case MarkCommand.COMMAND_WORD:
                return parseMark(args);
            case UnmarkCommand.COMMAND_WORD:
                return parseUnmark(args);
            case ListCommand.COMMAND_WORD:
                return parseList(args);
            case DeleteCommand.COMMAND_WORD:
                return parseDelete(args);
            case ByeCommand.COMMAND_WORD:
                return parseBye(args);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
        }
    }

    private Command parseTodo(String args) {
        if (args.isEmpty()) {
            return new InvalidCommand(TodoCommand.MESSAGE_EMPTY_DESCRIPTION, TodoCommand.MESSAGE_USAGE);
        }
        return new TodoCommand(new Todo(args));
    }

    private Command parseDeadline(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+) /by (?<by>.+)$");
        Matcher matcher = pattern.matcher(args);
        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND, DeadlineCommand.MESSAGE_USAGE);
        }
        String desc = matcher.group("desc").trim();
        String by = matcher.group("by").trim();
        return new DeadlineCommand(new Deadline(desc, by));
    }

    private Command parseEvent(String args) {
        Pattern pattern = Pattern.compile("^(?<desc>.+) /from (?<from>.+) /to (?<to>.+)$");
        Matcher matcher = pattern.matcher(args);
        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND, EventCommand.MESSAGE_USAGE);
        }
        String desc = matcher.group("desc").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();
        return new EventCommand(new Event(desc, from, to));
    }

    private Command parseMark(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MarkCommand.MESSAGE_INVALID_TASK_NUMBER, MarkCommand.MESSAGE_USAGE);
        }
    }

    private Command parseUnmark(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(UnmarkCommand.MESSAGE_INVALID_TASK_NUMBER, UnmarkCommand.MESSAGE_USAGE);
        }
    }

    private Command parseList(String args) {
        return new ListCommand();
    }

    private Command parseDelete(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(DeleteCommand.MESSAGE_INVALID_TASK_NUMBER, DeleteCommand.MESSAGE_USAGE);
        }
    }

    private Command parseBye(String args) {
        return new ByeCommand();
    }
}
