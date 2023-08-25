package minion.parser;

import minion.commands.*;
import minion.data.exception.IllegalValueException;
import minion.data.exception.MinionException;
import minion.data.exception.ParserException;
import minion.common.Messages;
import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.ToDo;

public class CommandParser {
    /**
     * Returns a task from the parsed command, throws an exception if there is no such task or invalid parameters.
     *
     * @param command minion.commands.Command to parse.
     * @return the task parsed from the command if no exception is thrown.
     * @throws ParserException when unable to parse command.
     * @throws IllegalValueException when illegal value in command.
     */
    public static Command parse(String command) throws MinionException {

        command = command.trim();

        if (command.isEmpty()) {
            throw new ParserException(Messages.MESSAGE_MISSING_COMMAND);
        }

        String[] arr = command.split(" ", 2);
        String commandWord = arr[0];

        switch (commandWord) {

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arr);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arr);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arr);

        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(arr);

        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arr);

        case EventCommand.COMMAND_WORD:
            return prepareEvent(arr);

        default:
            throw new ParserException(Messages.MESSAGE_NO_UNDERSTAND);
        }
    }

    private static int parseTaskIdx(String[] arr) throws MinionException {
        if (arr.length < 2 || arr[1].isEmpty() || !arr[1].trim().matches("[0-9]+")) {
            throw new MinionException("mark needs to have an argument. Try again.");
        }
       return Integer.parseInt(arr[1].trim()) - 1;
    }

    private static Command prepareMark(String[] arr) throws MinionException {
        int taskIdx = parseTaskIdx(arr);
        return new MarkCommand(taskIdx);
    }

    private static Command prepareUnmark(String[] arr) throws MinionException {
        int taskIdx = parseTaskIdx(arr);
        return new UnmarkCommand(taskIdx);
    }

    private static Command prepareDelete(String[] arr) throws MinionException {
        int taskIdx = parseTaskIdx(arr);
        return new DeleteCommand(taskIdx);
    }

    private static Command prepareToDo(String[] arr) throws MinionException {
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException(Messages.MESSAGE_TODO_DESCRIPTION_ERROR);
        }
        return new ToDoCommand(new ToDo(arr[1].trim()));
}

    private static Command prepareDeadline(String[] arr) throws MinionException {
        // nothing after deadline
        // or, something after deadline - but it's just empty space(s)
        // empty -> no description; non-empty -> still need to check if description is missing.
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException(Messages.MESSAGE_DEADLINE_DESCRIPTION_ERROR);
        }
        String[] strs = arr[1].split("/by");
        String description;
        switch (strs.length) {
        // nothing to left and right
        case 0:
            throw new ParserException(Messages.MESSAGE_DEADLINE_DESCRIPTION_ERROR);
        //something to left, nothing to the right
        case 1:
            description = strs[0].trim();
            if (description.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_DEADLINE_DESCRIPTION_ERROR);
            }
            throw new ParserException(Messages.MESSAGE_DEADLINE_BY_ERROR);
        case 2:
            description = strs[0].trim();
            String by = strs[1].trim();
            if (description.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_DEADLINE_DESCRIPTION_ERROR);
            }
            if (by.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_DEADLINE_BY_ERROR);
            }
            String datetime = DatetimeParser.parseDatetime(by.split(" "));
            return new DeadlineCommand(new Deadline(description, datetime));
        }
        return null;
    }

    private static Command prepareEvent(String[] arr) throws MinionException {
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException(Messages.MESSAGE_EVENT_DESCRIPTION_ERROR);
        }
        String[] strs = arr[1].split("/from");

        String description = null;

        switch (strs.length) {
        // nothing to left and right
        case 0:
            throw new ParserException(Messages.MESSAGE_EVENT_DESCRIPTION_ERROR);
        case 1:
            description = strs[0].trim();
            if (description.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_DESCRIPTION_ERROR);
            }
            throw new ParserException(Messages.MESSAGE_EVENT_FROM_ERROR);
        case 2:
            description = strs[0].trim();
            String dates = strs[1].trim();
            if (description.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_DESCRIPTION_ERROR);
            }
            if (dates.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_FROM_ERROR);
            }
            strs = strs[1].split("/to");
        }
        String from;
        String to;
        switch (strs.length) {
        // nothing to left and right
        case 0:
            throw new ParserException(Messages.MESSAGE_EVENT_FROM_ERROR);
        case 1:
            from = strs[0].trim();
            if (from.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_FROM_ERROR);
            }
            throw new ParserException(Messages.MESSAGE_EVENT_TO_ERROR);
        case 2:
            from = strs[0].trim();
            to = strs[1].trim();
            if (from.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_FROM_ERROR);
            }
            if (to.isEmpty()) {
                throw new ParserException(Messages.MESSAGE_EVENT_TO_ERROR);
            }
            String fromDatetime = DatetimeParser.parseDatetime(from.split(" "));
            String toDatetime = DatetimeParser.parseDatetime(to.split(" "));
            return new EventCommand(new Event(description, fromDatetime, toDatetime));
        }
        return null;
    }
}
