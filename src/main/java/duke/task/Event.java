package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 */

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor
     * @param description description of event
     * @param start start date of event
     * @param end end date of event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    //toString method
    @Override public String toString() {
        return "[E] " + super.toString() + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy ha"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy ha")) + ")";
    }

}
