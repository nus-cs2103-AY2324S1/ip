package task;

import java.time.LocalDateTime;
import datehandler.DateHandler;
import emiyaexception.WrongDateFormatException;

public class Deadline extends Task {

    private final String dateOfDeadline;
    private LocalDateTime localDateTime = null;

    public Deadline(boolean completed, String nameOfTask, String dateOfDeadline) throws WrongDateFormatException {
        super(completed, nameOfTask);
        this.localDateTime = DateHandler.determineDateTime(dateOfDeadline);
        this.dateOfDeadline = dateOfDeadline;
    }

    @Override
    public String toString() {
        if (completed) {
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
        return super.nameOfTask + " |" + " " + DateHandler.correctDateTimeFormat(localDateTime);
    }
}
