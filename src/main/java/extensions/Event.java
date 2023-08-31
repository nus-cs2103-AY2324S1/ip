package extensions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with starting and ending dates/times.
 */
public class Event extends Task {
    LocalDateTime fromDateTime;
    LocalDateTime toDateTime;
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }
    public String getDateTimeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }
    @Override
    public String getSaveFormat() {
        return String.format("E | %c | %s | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(this.fromDateTime),
                this.getDateTimeFormat(this.toDateTime));
    }
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s, to: %s)",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(this.fromDateTime),
                this.getDateTimeFormat(this.toDateTime));
    }

}