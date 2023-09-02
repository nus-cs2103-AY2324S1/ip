package emiya.task;

import java.time.LocalDateTime;

import emiya.datehandler.DateHandler;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;

public class Deadline extends Task {

    private final String dateOfDeadline;
    private LocalDateTime localDateTime = null;

    public Deadline(boolean isCompleted, String nameOfTask, String dateOfDeadline)
            throws WrongDateFormatException, InvalidDateException {
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
    public String typeOfString() {
        return "D ";
    }

    @Override
    public String printTaskDetailsString() {
        return super.taskDescription + " |" + " " + dateOfDeadline;
    }
}
