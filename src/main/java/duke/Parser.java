package duke;

import duke.command.*;
import duke.messages.ErrorMessages;
import duke.messages.Messages;
import duke.task.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public Command parse(String command) throws DukeException {
        if (command.contains("bye")) {
            if (command.length() == 3){
                return new ExitCommand();
            } else {
                throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'bye' ?");
            }
        } else if (command.contains("list")){
            if (command.length() == 4){
                return new ListCommand();
            } else {
                throw new DukeException(ErrorMessages.INVALID_INPUT.getMessage() + " 'list' ?");
            }
        } else if (command.contains("delete")) {
            if (command.length() == 6){
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                return new DeleteCommand(Integer.parseInt(command.substring(7)) - 1);
            }
        } else if (command.contains("todo")) {
            if (command.length() == 4){
                throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage() + "todo"
                        + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
            } else {
                Todo toDoTask = new Todo(command.substring(5));
                return new AddCommand(toDoTask);
            }
        } else if (command.contains("deadline")) {
            if (command.length() == 8){
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
                    LocalDateTime deadlineDate = LocalDateTime.parse(deadlineString[1].substring(3), formatter);
                    Deadline deadlineTask = new Deadline(deadlineString[0].trim(), deadlineDate);
                    return new AddCommand(deadlineTask);
                }
            }
        } else if (command.contains("event")) {
            if (command.length() == 5) {
                throw new DukeException(ErrorMessages.EMPTY_DESCRIPTION_HEAD.getMessage() + "event" + ErrorMessages.EMPTY_DESCRIPTION_TAIL.getMessage());
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
        } else if (command.contains("mark") && !command.contains("unmark")){
            if (command.length() == 4){
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                int taskNumber = Integer.parseInt(command.substring(5).trim()) - 1;
                return new MarkCommand(taskNumber);
            }
        } else if (command.contains("unmark")){
            if (command.length() == 6){
                throw new DukeException(ErrorMessages.MISSING_TASK_NUMBER.getMessage());
            } else {
                int taskNumber = Integer.parseInt(command.substring(7).trim()) - 1;
                return new UnmarkCommand(taskNumber);
            }
        }
        else {
            throw new DukeException(ErrorMessages.INCOMPREHENSIBLE_TASK.getMessage());
        }
    }


}
