package duke.taskClasses;

import duke.exception.InvalidDateTimeException;
import duke.utils.DateTimeUtils;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    public Event(String description, String start, String end) throws InvalidDateTimeException {
        super(description, "E");
        this.start = DateTimeUtils.stringToLocalDateTime(start);
        this.end = DateTimeUtils.stringToLocalDateTime(end);
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return String.format(" (from: %s to: %s)",
                DateTimeUtils.localDateTimeToString(start),
                DateTimeUtils.localDateTimeToString(end));
    }

    public String getDBString() {
        return String.format("%s | %s | %s | %s | %s",
                "E",this.isDone() ? "1" : "0",
                this.description,
                DateTimeUtils.localDateTimeToString(this.start),
                DateTimeUtils.localDateTimeToString(this.end));
    }
}
