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

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static duke.common.Messages.MESSAGE_INVALID_COMMAND;

/**
 * Parses user input into commands.
 */
public class Parser {
    private static final Pattern COMMAND_FORMAT = Pattern.compile("^(?<command>\\S+)(?<arguments>.*)$");

    public static Todo parseTodo(String args) {
        return new Todo(args);
    }

    /**
     * Parses a deadline input string into a Deadline object.
     *
     * @param args the input string for the deadline
     * @return the parsed Deadline object
     * @throws DukeParseException if unable to parse correctly
     */
    public static Deadline parseDeadline(String args) throws DukeParseException {
        Pattern pattern = Pattern.compile("^(?<desc>.+) /by (?<by>.+)$");
        Matcher matcher = pattern.matcher(args);
        if (!matcher.matches()) {
            throw new DukeParseException("Deadline cannot be parsed");
        }
        String desc = matcher.group("desc").trim();
        String by = matcher.group("by").trim();
        try {
            LocalDate byDate = LocalDate.parse(by);
            return new Deadline(desc, byDate);
        } catch (DateTimeParseException e) {
            throw new DukeParseException("Deadline cannot be parsed");
        }
    }

    /**
     * Parses an event input string into an Event object.
     *
     * @param args the input string for the event
     * @return the parsed Event object
     * @throws DukeParseException if unable to parse correctly
     */
    public static Event parseEvent(String args) throws DukeParseException {
        Pattern pattern = Pattern.compile("^(?<desc>.+) /from (?<from>.+) /to (?<to>.+)$");
        Matcher matcher = pattern.matcher(args);
        if (!matcher.matches()) {
            throw new DukeParseException("Event cannot be parsed");
        }
        String desc = matcher.group("desc").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();
        return new Event(desc, from, to);
    }

    /**
     * Takes a given user input and parses it into the corresponding command.
     * If the user supplies invalid input, an InvalidCommand will be returned.
     *
     * @param input the user input
     * @return the corresponding command from the input
     */
    public Command parseCommand(String input) {
        Matcher matcher = COMMAND_FORMAT.matcher(input);
        if (!matcher.matches()) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND);
        }
        String command = matcher.group("command").trim();
        String args = matcher.group("arguments").trim();

        switch (command) {
            case TodoCommand.COMMAND_WORD:
                return parseTodoCommand(args);
            case DeadlineCommand.COMMAND_WORD:
                return parseDeadlineCommand(args);
            case EventCommand.COMMAND_WORD:
                return parseEventCommand(args);
            case MarkCommand.COMMAND_WORD:
                return parseMarkCommand(args);
            case UnmarkCommand.COMMAND_WORD:
                return parseUnmarkCommand(args);
            case ListCommand.COMMAND_WORD:
                return parseListCommand(args);
            case DeleteCommand.COMMAND_WORD:
                return parseDeleteCommand(args);
            case ByeCommand.COMMAND_WORD:
                return parseByeCommand(args);
            default:
                return new InvalidCommand(MESSAGE_INVALID_COMMAND);
        }
    }

    /**
     * Parses a given argument string for the Todo command.
     *
     * @param args argument string for the Todo command
     * @return the parsed TodoCommand object
     */
    private Command parseTodoCommand(String args) {
        if (args.isEmpty()) {
            return new InvalidCommand(TodoCommand.MESSAGE_EMPTY_DESCRIPTION, TodoCommand.MESSAGE_USAGE);
        }
        return new TodoCommand(parseTodo(args));
    }

    /**
     * Parses a given argument string for the Deadline command.
     *
     * @param args argument string for the Deadline command
     * @return the parsed DeadlineCommand object
     */
    private Command parseDeadlineCommand(String args) {
        try {
            Deadline deadline = parseDeadline(args);
            return new DeadlineCommand(deadline);
        } catch (DukeParseException e) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND, e.getMessage(), DeadlineCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a given argument string for the Event command.
     *
     * @param args argument string for the Event command
     * @return the parsed EventCommand object
     */
    private Command parseEventCommand(String args) {
        try {
            Event event = parseEvent(args);
            return new EventCommand(event);
        } catch (DukeParseException e) {
            return new InvalidCommand(MESSAGE_INVALID_COMMAND, EventCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a given argument string for the Mark command.
     *
     * @param args argument string for the Mark command
     * @return the parsed MarkCommand object
     */
    private Command parseMarkCommand(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(MarkCommand.MESSAGE_INVALID_TASK_NUMBER, MarkCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a given argument string for the Unmark command.
     *
     * @param args argument string for the Unmark command
     * @return the parsed UnmarkCommand object
     */
    private Command parseUnmarkCommand(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new UnmarkCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(UnmarkCommand.MESSAGE_INVALID_TASK_NUMBER, UnmarkCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a given argument string for the List command.
     * A List command has no arguments to parse, but we have this function for uniformity.
     *
     * @param args argument string for the List command
     * @return the parsed ListCommand object
     */
    private Command parseListCommand(String args) {
        return new ListCommand();
    }

    /**
     * Parses a given argument string for the Delete command.
     *
     * @param args argument string for the Delete command
     * @return the parsed DeleteCommand object
     */
    private Command parseDeleteCommand(String args) {
        try {
            int taskNum = Integer.parseInt(args);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            return new InvalidCommand(DeleteCommand.MESSAGE_INVALID_TASK_NUMBER, DeleteCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses a given argument string for the Bye command.
     * A Bye command has no arguments to parse, but we have this function for uniformity.
     *
     * @param args argument string for the Bye command
     * @return the parsed ByeCommand object
     */
    private Command parseByeCommand(String args) {
        return new ByeCommand();
    }
}
