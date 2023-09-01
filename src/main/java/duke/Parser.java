package duke;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnmarkCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Parses user input
 */
public class Parser {
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses user input into command for execution.
     *
     * @param input user input string
     * @return the command based on the user input
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.equals("bye")) {
            return new ExitCommand();
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

    /**
     * Parses arguments in the context of the mark task command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static MarkCommand prepareMark(String[] details) throws DukeException {
        // user input only has the command eg "mark"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! "
                    + "Please include the index of the task you wish to mark");
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
        // user input only has the command eg "unmark"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! "
                    + "Please include the index of the task you wish to unmark");
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
        // user input only has the command eg "delete"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! "
                    + "Please include the index of the task you wish to delete");
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
        // user input only has the command eg "todo"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include details of this task");
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
        // user input only has the command eg "deadline"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include details of this task");
        }
        String[] deadline = details[1].split("/by", 2);
        // user input does not include /by
        if (deadline.length < 2 || deadline[1].trim().isEmpty()) { // user input does not have /by
            throw new DukeException("Invalid command! Please include the deadline of this task");
        }
        // user input is missing the description of the deadline
        if (deadline[0].split(" ", 2).length < 2) {
            throw new DukeException("Invalid command! Please include details of this task");
        }
        LocalDateTime by = LocalDateTime.parse(deadline[1].trim(), inputFormatter);
        return new DeadlineCommand(deadline[0].trim(), by);
    }

    /**
     * Parses arguments in the context of the add event command
     *
     * @param details full command args string
     * @return the prepared command
     */
    private static EventCommand prepareEvent(String[] details) throws DukeException {
        // user input only has the command eg "event"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include details of this task");
        }
        String[] eventDetails = details[1].split("/from", 2);
        // user input does not include /from
        if (eventDetails.length < 2 || eventDetails[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include when the event starts");
        }
        // user input does not include the description of the event
        if (eventDetails[0].split(" ", 2).length < 2) {
            throw new DukeException("Invalid command! Please include details of this task");
        }
        String[] eventTimings = eventDetails[1].split("/to", 2);
        // user input does not include /to
        if (eventTimings.length < 2 || eventTimings[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include when the event ends");
        }
        LocalDateTime from = LocalDateTime.parse(eventTimings[0].trim(), inputFormatter);
        LocalDateTime to = LocalDateTime.parse(eventTimings[1].trim(), inputFormatter);
        return new EventCommand(eventDetails[0].trim(), from, to);
    }

    private static FindCommand prepareFind (String[] details) throws DukeException {
        // user input only has the command eg "event"
        if (details.length < 2 || details[1].trim().isEmpty()) {
            throw new DukeException("Invalid command! Please include a search keyword");
        }
        return new FindCommand(details[1].trim());
    }
}
