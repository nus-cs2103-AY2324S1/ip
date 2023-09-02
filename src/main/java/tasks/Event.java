package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /*
     * Constructor for the Event object.
     *
     * @param start The LocalDateTime start.
     * @param end The end date and time in a LocalDateTime object.
     * @param description The description of the event.
     */
    public Event(LocalDateTime start, LocalDateTime end, String description) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /*
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[E]" + super.toString() + "(from: " + this.start.format(format) + " to: " + this.end.format(format) + ")";
    }

    /*
     * Returns the string version of the event, for use in writing to storage.
     *
     * @return The string version of the event.
     */
    @Override
    public String toStringWithDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return "[E]" + super.toString() + " DATETIME " + this.start.format(format) + " DATETIME_SPLIT " + this.end.format(format);
    }
}
