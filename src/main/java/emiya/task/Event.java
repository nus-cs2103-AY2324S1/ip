package emiya.task;

import java.time.LocalDateTime;

import emiya.datehandler.DateHandler;
import emiya.emiyaexception.InvalidDateTimeException;
import emiya.emiyaexception.WrongDateTimeFormatException;

/**
 * A class representing the Event tasks that the user can create.
 */
public class Event extends Task {

    private final String start;
    private final String end;
    private LocalDateTime localDateTimeStart = null;
    private LocalDateTime localDateTimeEnd = null;

    public Event(boolean isCompleted, String nameOfTask, String start, String end)
            throws WrongDateTimeFormatException, InvalidDateTimeException {
        super(isCompleted, nameOfTask);
        this.start = start;
        this.end = end;
        this.localDateTimeStart = DateHandler.determineDateTime(start);
        this.localDateTimeEnd = DateHandler.determineDateTime(end);
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[E][X] " + taskDescription + " (from: " + DateHandler.correctDateTimeFormat(localDateTimeStart)
                    + " to: " + DateHandler.correctDateTimeFormat(localDateTimeEnd) + ")";
        } else {
            return "[E][ ] " + taskDescription + " (from: " + DateHandler.correctDateTimeFormat(localDateTimeStart)
                    + " to: " + DateHandler.correctDateTimeFormat(localDateTimeEnd) + ")";
        }
    }

    @Override
    public String printTypeOfTask() {
        return "E ";
    }

    @Override
    public String printTaskDetailsString() {
        return super.taskDescription + " |" + " " + start + " |" + " " + end;
    }
}
