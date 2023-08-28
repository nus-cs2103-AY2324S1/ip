package bruno.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime by;

    public Event(String description, String from, String by) {
        super(TaskType.EVENT, description);
        this.from = this.convertToLocalDateTime(from);
        this.by = this.convertToLocalDateTime(by);
    }

    @Override public String getString() throws DateTimeException {
        return "[E]" + super.getString() + " (from: " + convertDateTimeToString(from) + " to: "
                + convertDateTimeToString(by) + ")";
    }

    @Override public String getFileString() throws DateTimeException {
        return "E|" + super.getFileString() + "|" + from.toString().replace('T', ' ') + "|" + by.toString()
                .replace('T', ' ');
    }
}
