package duke;

import duke.command.*;
import duke.messages.ErrorMessages;
import duke.messages.Messages;
import duke.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            if (command.trim().length() == 3) {
                return new ExitCommand();
            } else {
                throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'bye' ?");
            }
        } else if (command.contains("list")){
            if (command.trim().length() == 4) {
                return new ListCommand();
            } else {
                throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'list' ?");
            }
        } else if (command.contains("delete")) {
            if (command.trim().length() == 6) {
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                int deleteNumber = Integer.parseInt(command.substring(7));
                assert deleteNumber > 0 : "Not Valid Number";
                return new DeleteCommand(deleteNumber);
            }
        } else if (command.contains("todo")) {
            if (command.trim().length() == 4) {
                throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage() + "todo"
                        + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
            } else {
                Todo toDoTask = new Todo(command.substring(5));
                return new AddCommand(toDoTask);
            }
        } else if (command.contains("deadline")) {
            if (command.trim().length() == 8) {
                throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage() +
                        "deadline" + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
            } else {
                String[] deadlineString = command.substring(9).split("/");
                if (deadlineString.length == 1) {
                    // Check if deadline is present.
                    throw new DukeException(ErrorMessages.MISSING_DEADLINE.getMessage());
                } else {
                    // Deal with DateTimeParseException
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Messages.DATE_FORMAT.getMessage());
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString[1].substring(3),
                            formatter);
                    Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineDate);
                    return new AddCommand(deadlineTask);
                }
            }
        } else if (command.contains("event")) {
            if (command.trim().length() == 5) {
                throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage() + "event" +
                        ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
            } else {
                String[] eventString = command.substring(6).split("/");
                // Check if a start and end time has been provided.
                if (eventString.length < 3) {
                    throw new DukeException(ErrorMessages.MISSING_EVENT_TIMING.getMessage());
                } else if (eventString.length > 3) {
                    throw new DukeException(ErrorMessages.TOO_MANY_EVENT_TIMINGS.getMessage());
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Messages.DATE_FORMAT.getMessage());
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
        } else if (command.contains("mark") && !command.contains("unmark")) {
            if (command.trim().length() == 4) {
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                int taskNumber = Integer.parseInt(command.substring(5).trim());
                assert taskNumber > 0 : "Not Valid Number";
                return new MarkCommand(taskNumber);
            }
        } else if (command.contains("unmark")) {
            if (command.trim().length() == 6) {
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                int taskNumber = Integer.parseInt(command.substring(7).trim());
                assert taskNumber > 0 : "Not Valid Number";
                return new UnmarkCommand(taskNumber);
            }
        } else if (command.contains("find")) {
            if (command.trim().length() == 4) {
                throw new DukeException(ErrorMessages.NO_KEYWORD_PROVIDED.getMessage());
            } else {
                String input = command.substring(5).trim();
                return new FindCommand(input);
            }
        } else if (command.contains("help")){
            if (command.trim().length() == 4){
                return new HelpCommand(0);
            } else {
                int helpNumber = Integer.parseInt(command.substring(5));
                return new HelpCommand(helpNumber);
            }
        }
        else {
            throw new DukeException(ErrorMessages.INCOMPREHENSIBLE_TASK.getMessage());
        }
    }


}
