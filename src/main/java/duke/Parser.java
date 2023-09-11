package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

/**
 * Parses user input
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final String MISSING_INDEX_MESSAGE = "Invalid command! Please include the index of the task";
    private static final String MISSING_DETAILS_MESSAGE = "Invalid command! Please include the details of the task";

    /**
     * Parses user input into command for execution.
     *
     * @param input user input string
     * @return the command based on the user input
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
        } else {
            String[] details = input.split(" ", 2);
            String commandName = details[0];
            switch (commandName) {
            case MarkCommand.COMMAND_WORD:
                return prepareMark(details);
            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(details);
            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(details);
            case TodoCommand.COMMAND_WORD:
                return prepareTodo(details);
            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(details);
            case EventCommand.COMMAND_WORD:
                return prepareEvent(details);
            case FindCommand.COMMAND_WORD:
                return prepareFind(details);
            default:
                throw new DukeException("Sorry! I do not recognise this command");
            }
        }
    }

    private static boolean isInvalidCommand(String[] details) {
        return (details.length < 2 || details[1].trim().isEmpty());
    }

    private static boolean isMissingDescription(String[] details) {
        return (details[0].split(" ", 2).length < 2);
    }

    /**
     * Parses arguments in the context of the mark task command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static MarkCommand prepareMark(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_INDEX_MESSAGE);
        }
        int markTaskId = Integer.parseInt(details[1]) - 1;
        return new MarkCommand(markTaskId);
    }

    /**
     * Parses arguments in the context of the unmark task command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static UnmarkCommand prepareUnmark(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_INDEX_MESSAGE);
        }
        int unmarkTaskId = Integer.parseInt(details[1]) - 1;
        return new UnmarkCommand(unmarkTaskId);
    }

    /**
     * Parses arguments in the context of the delete task command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static DeleteCommand prepareDelete(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_INDEX_MESSAGE);
        }
        int deleteTaskId = Integer.parseInt(details[1]) - 1;
        return new DeleteCommand(deleteTaskId);
    }

    /**
     * Parses arguments in the context of the add todo command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static TodoCommand prepareTodo(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_DETAILS_MESSAGE);
        }
        String todoDesc = details[1].trim();
        return new TodoCommand(todoDesc);
    }

    /**
     * Parses arguments in the context of the add deadline command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static DeadlineCommand prepareDeadline(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_DETAILS_MESSAGE);
        }
        String[] deadline = details[1].split("/by", 2);
        if (isInvalidCommand(deadline)) {
            throw new DukeException("Invalid command! Please include the deadline of this task");
        }
        if (isMissingDescription(deadline)) {
            throw new DukeException(MISSING_DETAILS_MESSAGE);
        }
        LocalDateTime by = LocalDateTime.parse(deadline[1].trim(), INPUT_FORMATTER);
        return new DeadlineCommand(deadline[0].trim(), by);
    }

    /**
     * Parses arguments in the context of the add event command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static EventCommand prepareEvent(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException(MISSING_DETAILS_MESSAGE);
        }
        String[] eventDetails = details[1].split("/from", 2);
        if (isInvalidCommand(eventDetails)) {
            throw new DukeException("Invalid command! Please include when the event starts");
        }
        if (isMissingDescription(eventDetails)) {
            throw new DukeException(MISSING_DETAILS_MESSAGE);
        }
        String[] eventTimings = eventDetails[1].split("/to", 2);
        if (isInvalidCommand(eventTimings)) {
            throw new DukeException("Invalid command! Please include when the event ends");
        }
        LocalDateTime from = LocalDateTime.parse(eventTimings[0].trim(), INPUT_FORMATTER);
        LocalDateTime to = LocalDateTime.parse(eventTimings[1].trim(), INPUT_FORMATTER);
        return new EventCommand(eventDetails[0].trim(), from, to);
    }

    private static FindCommand prepareFind(String[] details) throws DukeException {
        if (isInvalidCommand(details)) {
            throw new DukeException("Invalid command! Please include a search keyword");
        }
        return new FindCommand(details[1].trim());
    }
}
