package emiya.task;

import java.time.LocalDateTime;

import emiya.datehandler.DateHandler;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;

public class Event extends Task {

    private final String start;
    private final String end;
    private LocalDateTime localDateTimeStart = null;
    private LocalDateTime localDateTimeEnd = null;

    public Event(boolean isCompleted, String nameOfTask, String start, String end)
            throws WrongDateFormatException, InvalidDateException {
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
    public String typeOfString() {
        return "E ";
    }

    @Override
    public String printTaskDetailsString() {
        return super.taskDescription + " |" + " " + start + " |" + " " + end;
    }
}
