package task;

import java.time.LocalDateTime;
import datehandler.DateHandler;
import emiyaexception.InvalidDateException;
import emiyaexception.WrongDateFormatException;

public class Deadline extends Task {

    private final String dateOfDeadline;
    private LocalDateTime localDateTime = null;

    public Deadline(boolean isCompleted, String nameOfTask, String dateOfDeadline) throws WrongDateFormatException, InvalidDateException {
        super(isCompleted, nameOfTask);
        this.localDateTime = DateHandler.determineDateTime(dateOfDeadline);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[D][X] " + nameOfTask + " (by: " + DateHandler.correctDateTimeFormat(localDateTime) + ")";
        } else {
            return "[D][ ] " + nameOfTask + " (by: " + DateHandler.correctDateTimeFormat(localDateTime) + ")";
        }
    }

    @Override
    public String typeOfString() {
        return "D ";
    }

    @Override
    public String taskDetailsString() {
        return super.nameOfTask + " |" + " " + dateOfDeadline;
    }
}
