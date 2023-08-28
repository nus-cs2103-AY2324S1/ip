package pogo.parsers;

import pogo.commands.*;
import pogo.common.Messages;
import pogo.tasks.TaskType;
import pogo.tasks.exceptions.PogoInvalidTaskException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {
    public static TaskType toTaskType(String input) {
        if (input.startsWith("todo") || input.startsWith("T")) {
            return TaskType.TODO;
        } else if (input.startsWith("deadline") || input.startsWith("D")) {
            return TaskType.DEADLINE;
        } else if (input.startsWith("event") || input.startsWith("E")) {
            return TaskType.EVENT;
        } else {
            return null;
        }
    }

    /**
     * Parses a deadline command.
     * A valid deadline command has the format "deadline <description> /by <by>".
     * @param input The input string.
     * @return The AddDeadlineCommand object.
     * @throws PogoInvalidTaskException If the input string is invalid.
     */
    public static AddDeadlineCommand parseDeadlineCommand(String input) throws PogoInvalidTaskException {
        final Pattern DEADLINE_PATTERN = Pattern.compile("(?<description>.*) /by (?<by>.*)");
        final Matcher matcher = DEADLINE_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new PogoInvalidTaskException();
        }

        String description = matcher.group("description");
        String by = matcher.group("by");
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses a todo command.
     * A valid todo command has the format "todo <description>".
     * @param input The input string.
     * @return The AddToDoCommand object.
     * @throws PogoInvalidTaskException If the input string is invalid.
     */
    public static AddToDoCommand parseToDoCommand(String input) throws PogoInvalidTaskException {
        final Pattern TODO_PATTERN = Pattern.compile("(?<description>.*)");
        final Matcher matcher = TODO_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new PogoInvalidTaskException();
        }

        String description = matcher.group("description");
        return new AddToDoCommand(description);
    }

    /**
     * Parses an event command.
     * A valid event command has the format "event <description> /from <from> /to <to>".
     * @param input The input string.
     * @return The AddEventCommand object.
     * @throws PogoInvalidTaskException If the input string is invalid.
     */
    public static AddEventCommand parseEventCommand(String input) throws PogoInvalidTaskException {
        final Pattern EVENT_PATTERN = Pattern.compile("(?<description>.*) /from (?<from>.*) /to (?<to>.*)");
        final Matcher matcher = EVENT_PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new PogoInvalidTaskException();
        }

        String description = matcher.group("description");
        String from = matcher.group("from");
        String to = matcher.group("to");
        return new AddEventCommand(description, from, to);
    }

    /**
     * Parses the index of a task.
     * @param input The input string.
     * @return The index of the task.
     */
    private static int parseIndex(String input) {
        final Pattern MARK_PATTERN = Pattern.compile("(?<index>\\d+)");
        final Matcher matcher = MARK_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return -1;
        }

        try {
            return Integer.parseInt(matcher.group("index")) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Parses a "mark task as done" command.
     * @param input The input string.
     * @param isMark Whether the command is a mark command or an unmark command.
     */
    public static Command parseMarkCommand(String input, boolean isMark) {
        int index = parseIndex(input);
        if (index < 0) {
            return new InvalidCommand(Messages.INVALID_INDEX);
        }

        if (isMark) {
            return new MarkTaskCommand(index);
        } else {
            return new UnmarkTaskCommand(index);
        }
    }

    /**
     * Parses a "delete task" command.
     * @param input The input string.
     * @return The DeleteTaskCommand object.
     */
    public static Command parseDeleteCommand(String input) {
        int index = parseIndex(input);
        if (index < 0) {
            return new InvalidCommand(Messages.INVALID_INDEX);
        }

        return new DeleteTaskCommand(index);
    }
}
