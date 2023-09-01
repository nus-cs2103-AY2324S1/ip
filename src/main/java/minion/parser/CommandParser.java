package minion.parser;

import minion.commands.Command;
import minion.commands.DeadlineCommand;
import minion.commands.DeleteCommand;
import minion.commands.EventCommand;
import minion.commands.ExitCommand;
import minion.commands.FindCommand;
import minion.commands.ListCommand;
import minion.commands.MarkCommand;
import minion.commands.ToDoCommand;
import minion.commands.UnmarkCommand;
import minion.common.Messages;
import minion.data.exception.MinionException;
import minion.data.exception.ParserException;
import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.ToDo;

/**
 * Represents a command parser.
 */
public class CommandParser {

    /**
     * Parses a task from the command. Throws an exception if unable to parse or invalid argument(s) provided.
     * @param command command given.
     * @return the task parsed.
     * @throws MinionException if unable to parse or invalid argument(s) provided.
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

        case FindCommand.COMMAND_WORD:
            return prepareFind(arr);

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

    /**
     * Parses task index from an array of strings.
     * @param arr Array of string parts.
     * @return task index parsed.
     * @throws ParserException if unable to parse task index.
     */
    private static int parseTaskIdx(String commandWord, String[] arr) throws ParserException {
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException(commandWord + " needs to have an argument. Try again.");
        }
        if (!arr[1].trim().matches("[0-9]+")) {
            throw new ParserException(commandWord + " only accepts digits as its argument. Try again.");
        }
        return Integer.parseInt(arr[1].trim()) - 1;
    }

    /**
     * Parses arguments in the context of the mark command.
     * @param arr Array of string parts.
     * @return Mark command.
     * @throws ParserException if unable to parse task index.
     */
    private static Command prepareMark(String[] arr) throws ParserException {
        int taskIdx = parseTaskIdx(MarkCommand.COMMAND_WORD, arr);
        return new MarkCommand(taskIdx);
    }

    /**
     * Parses arguments in the context of the unmark command.
     * @param arr Array of string parts.
     * @return Unmark command.
     * @throws ParserException if unable to parse task index.
     */
    private static Command prepareUnmark(String[] arr) throws ParserException {
        int taskIdx = parseTaskIdx(UnmarkCommand.COMMAND_WORD, arr);
        return new UnmarkCommand(taskIdx);
    }

    /**
     * Parses arguments in the context of the delete command.
     * @param arr Array of string parts.
     * @return Delete command.
     * @throws ParserException if unable to parse task index.
     */
    private static Command prepareDelete(String[] arr) throws ParserException {
        int taskIdx = parseTaskIdx(DeleteCommand.COMMAND_WORD, arr);
        return new DeleteCommand(taskIdx);
    }

    /**
     * Parses arguments in the context of the ToDo command.
     * @param arr Array of string parts.
     * @return ToDo command.
     * @throws ParserException if unable to parse the command.
     */
    private static Command prepareToDo(String[] arr) throws ParserException {
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException(Messages.MESSAGE_TODO_DESCRIPTION_ERROR);
        }
        return new ToDoCommand(new ToDo(arr[1].trim()));
    }

    /**
     * Parses arguments in the context of the Deadline command.
     * @param arr Array of string parts.
     * @return Deadline command.
     * @throws ParserException if unable to parse the command.
     */
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

        default:
            break;
        }
        return null;
    }

    /**
     * Parses arguments in the context of the Event command.
     * @param arr Array of string parts.
     * @return Event command.
     * @throws MinionException if unable to parse the command.
     */
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
            break;

        default:
            break;
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

        default:
            break;
        }
        return null;
    }

    /**
     * Parses arguments in the context of the find command.
     * @param arr Array of strings.
     * @return Find command.
     * @throws ParserException when unable to parse the command.
     */
    private static Command prepareFind(String[] arr) throws ParserException {
        if (arr.length < 2 || arr[1].isEmpty()) {
            throw new ParserException("Find needs to have an argument. Try again.");
        }
        return new FindCommand(arr[1].trim());
    }
}
