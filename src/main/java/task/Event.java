package task;

import datehandler.DateHandler;
import emiyaexception.InvalidDateException;
import emiyaexception.WrongDateFormatException;

import java.time.LocalDateTime;

public class Event extends Task {

    private final String start;
    private final String end;
    private LocalDateTime localDateTimeStart = null;
    private LocalDateTime localDateTimeEnd = null;

    public Event(boolean isCompleted, String nameOfTask, String start, String end) throws WrongDateFormatException, InvalidDateException {
        super(isCompleted, nameOfTask);
        this.start = start;
        this.end = end;
        this.localDateTimeStart = DateHandler.determineDateTime(start);
        this.localDateTimeEnd = DateHandler.determineDateTime(end);
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[E][X] " + nameOfTask + " (from: " + DateHandler.correctDateTimeFormat(localDateTimeStart) + " to: " + DateHandler.correctDateTimeFormat(localDateTimeEnd) + ")";
        } else {
            return "[E][ ] " + nameOfTask + " (from: " + DateHandler.correctDateTimeFormat(localDateTimeStart) + " to: " + DateHandler.correctDateTimeFormat(localDateTimeEnd) + ")";
        }
    }

    @Override
    public String typeOfString() {
        return "E ";
    }

    @Override
    public String taskDetailsString() {
        return super.nameOfTask + " |" + " " + start + " |" + " " + end;
    }
}
