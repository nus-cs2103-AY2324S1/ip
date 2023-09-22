package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.messages.ErrorMessages;
import duke.messages.Messages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Parses the user input into the respective command.
 */

public class Parser {

    /**
     * Returns the appropriate command to be executed.
     * @param command the command inputted by the user
     * @return the appropriate command to be executed
     * @throws DukeException if command is not recognised, an error is thrown
     */
    public Command parse(String command) throws DukeException {
        if (command.contains("bye")) {
            return exitCommand(command);
        } else if (command.contains("list")) {
            return listCommand(command);
        } else if (command.contains("delete")) {
            return deleteCommand(command);
        } else if (command.contains("todo")) {
            return addToDoCommand(command);
        } else if (command.contains("deadline")) {
            return addDeadlineCommand(command);
        } else if (command.contains("event")) {
            return addEventCommand(command);
        } else if (command.contains("mark") && !command.contains("unmark")) {
            return markCommand(command);
        } else if (command.contains("unmark")) {
            return unmarkCommand(command);
        } else if (command.contains("find")) {
            return findCommand(command);
        } else if (command.contains("help")) {
            return helpCommand(command);
        } else {
            throw new DukeException(ErrorMessages.INCOMPREHENSIBLE_TASK.getMessage());
        }
    }

    /**
     * Parses the bye string command input
     * @param command String representation of command
     * @return ExitCommand
     * @throws DukeException invalid bye command input
     */
    private ExitCommand exitCommand(String command) throws DukeException {
        if (command.trim().length() != 3) {
            throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'bye' ?");
        } else {
            return new ExitCommand();
        }
    }

    /**
     * Parses the list string command input
     * @param command String representation of command
     * @return ListCommand
     * @throws DukeException invalid list command input
     */
    private ListCommand listCommand(String command) throws DukeException {
        if (command.trim().length() != 4) {
            throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'list' ?");
        } else {
            return new ListCommand();
        }
    }

    /**
     * Parses the delete string command input
     * @param command String representation of command
     * @return DeleteCommand
     * @throws DukeException missing or negative number provided
     */
    private DeleteCommand deleteCommand(String command) throws DukeException {
        if (command.trim().length() == 6) {
            throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
        } else {
            int deleteNumber = Integer.parseInt(command.substring(7));
            assert deleteNumber > 0 : "Not Valid Number";
            return new DeleteCommand(deleteNumber);
        }
    }

    /**
     * Parses the add Todo task string command input
     * @param command String representation of command
     * @return AddCommand
     * @throws DukeException missing todo task description
     */
    private AddCommand addToDoCommand(String command) throws DukeException {
        if (command.trim().length() == 4) {
            throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage()
                    + "todo"
                    + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
        } else {
            Todo toDoTask = new Todo(command.substring(5));
            return new AddCommand(toDoTask);
        }
    }

    /**
     * Parses the add Deadline task string command input
     * @param command String representation of command
     * @return AddCommand
     * @throws DukeException missing deadline description or date
     */
    private AddCommand addDeadlineCommand(String command) throws DukeException {
        if (command.trim().length() == 8) {
            throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage()
                    + "deadline" + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
        } else {
            String[] deadlineString = command.substring(9).split("/");
            if (deadlineString.length == 1) {
                // Check if deadline is present.
                throw new DukeException(ErrorMessages.MISSING_DEADLINE.getMessage());
            } else {
                // Deal with DateTimeParseException
                DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern(Messages.DATE_FORMAT.getMessage()).toFormatter(Locale.ENGLISH);
                LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString[1].substring(3).trim(),
                        formatter);
                Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineDate);
                return new AddCommand(deadlineTask);
            }
        }
    }

    /**
     * Parses the add Event task string command input
     * @param command String representation of command
     * @return AddCommand
     * @throws DukeException missing event description, start or end date
     */
    private AddCommand addEventCommand(String command) throws DukeException {
        if (command.trim().length() == 5) {
            throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage()
                    + "event"
                    + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
        } else {
            String[] eventString = command.substring(6).split("/");
            // Check if a start and end time has been provided.
            if (eventString.length < 3) {
                throw new DukeException(ErrorMessages.MISSING_EVENT_TIMING.getMessage());
            } else if (eventString.length > 3) {
                throw new DukeException(ErrorMessages.TOO_MANY_EVENT_TIMINGS.getMessage());
            } else {
                DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                        .appendPattern(Messages.DATE_FORMAT.getMessage()).toFormatter(Locale.ENGLISH);
                LocalDateTime startDate = LocalDateTime.parse(eventString[1].substring(5).trim(), formatter);
                LocalDateTime endDate = LocalDateTime.parse(eventString[2].substring(3).trim(), formatter);
                if (endDate.isBefore(startDate)) {
                    throw new DukeException(ErrorMessages.INVALID_END_DATE.getMessage());
                } else {
                    Event eventTask = new Event(eventString[0].trim(), startDate, endDate);
                    return new AddCommand(eventTask);
                }
            }
        }
    }

    /**
     * Parses the mark string command input
     * @param command String representation of command
     * @return MarkCommand
     * @throws DukeException missing or negative task number
     */
    private MarkCommand markCommand(String command) throws DukeException {
        if (command.trim().length() == 4) {
            throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
        } else {
            int taskNumber = Integer.parseInt(command.substring(5).trim());
            assert taskNumber > 0 : "Not Valid Number";
            return new MarkCommand(taskNumber);
        }
    }

    /**
     * Parses the unmark string command input
     * @param command String representation of command
     * @return UnmarkCommand
     * @throws DukeException missing or negative task number
     */
    private UnmarkCommand unmarkCommand(String command) throws DukeException {
        if (command.trim().length() == 6) {
            throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
        } else {
            int taskNumber = Integer.parseInt(command.substring(7).trim());
            assert taskNumber > 0 : "Not Valid Number";
            return new UnmarkCommand(taskNumber);
        }
    }

    /**
     * Parses the find string command input
     * @param command String representation of command
     * @return FindCommand
     * @throws DukeException no task found related to the keyword
     */
    private FindCommand findCommand(String command) throws DukeException {
        if (command.trim().length() == 4) {
            throw new DukeException(ErrorMessages.NO_KEYWORD_PROVIDED.getMessage());
        } else {
            String input = command.substring(5).trim();
            return new FindCommand(input);
        }
    }

    /**
     * Parses the help string command input
     * @param command String representation of command
     * @return HelpCommand
     */
    private HelpCommand helpCommand(String command) {
        if (command.trim().length() == 4) {
            return new HelpCommand(0);
        } else {
            int helpNumber = Integer.parseInt(command.substring(5));
            return new HelpCommand(helpNumber);
        }
    }
}
