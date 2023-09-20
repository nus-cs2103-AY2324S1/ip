package emiya.task;

import java.time.LocalDateTime;

import emiya.datehandler.DateHandler;
import emiya.emiyaexception.InvalidDateTimeException;
import emiya.emiyaexception.WrongDateTimeFormatException;

/**
 * A class representing the Deadline tasks that the user can create.
 */
public class Deadline extends Task {

    private final String dateOfDeadline;
    private LocalDateTime localDateTime = null;

    public Deadline(boolean isCompleted, String nameOfTask, String dateOfDeadline)
            throws WrongDateTimeFormatException, InvalidDateTimeException {
        super(isCompleted, nameOfTask);
        this.localDateTime = DateHandler.determineDateTime(dateOfDeadline);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[D][X] " + taskDescription + " (by: " + DateHandler.correctDateTimeFormat(localDateTime) + ")";
        } else {
            return "[D][ ] " + taskDescription + " (by: " + DateHandler.correctDateTimeFormat(localDateTime) + ")";
        }
    }

    @Override
    public String printTypeOfTask() {
        return "D ";
    }

    @Override
    public String printTaskDetailsString() {
        return super.taskDescription + " | " + dateOfDeadline + " | " + " ";
    }
}
