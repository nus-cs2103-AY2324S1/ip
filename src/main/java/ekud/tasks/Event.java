package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with starting and ending dates/times.
 */
public class Event extends Task {
    // Start and end dateTimes associated with this event.
    LocalDateTime fromDateTime;
    LocalDateTime toDateTime;
    public Event(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Private method used to help format this event's dateTime into a
     * more user-friendly format.
     * @param dateTime
     * @return String
     */
    private String getDateTimeFormat(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a"));
    }

    /**
     * Returns the compact saved format for this event.
     * @return String
     */
    @Override
    public String getSaveFormat() {
        return String.format("E | %c | %s | %s | %s",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(this.fromDateTime),
                this.getDateTimeFormat(this.toDateTime));
    }

    /**
     * String representation of this event as a task.
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[E][%c] %s (from: %s, to: %s)",
                this.getDoneSymbol(),
                this.description,
                this.getDateTimeFormat(this.fromDateTime),
                this.getDateTimeFormat(this.toDateTime));
    }

}