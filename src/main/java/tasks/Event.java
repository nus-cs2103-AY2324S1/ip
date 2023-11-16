package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start time, and end time.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    protected LocalDateTime start;

    /**
     * The end time of the event.
     */
    protected LocalDateTime end;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event.
     * @param end         The end time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        String newStart = start.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        String newEnd = end.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return "[E]" + super.toString() + " (from: " + newStart + " to: " + newEnd + ")";
    }
}

