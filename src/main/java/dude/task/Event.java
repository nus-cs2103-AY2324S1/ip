package dude.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a scheduled event that extends a task.
 * An <code>Event</code> object has a <code>String</code> description,
 * <code>LocalDateTime</code> start time, and a <code>LocalDateTime</code> end time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event object with the specified description and event date and time.
     *
     * @param description A short description of the event.
     * @param from The time when the event is scheduled to start.
     * @param to The time when the event is scheduled to end.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getType() {
        return "event";
    }

    @Override
    public String saveTask() {
        String data = "E " + super.saveTask() + " | " + this.from + " | " + this.to + "\n";
        return data;
    }

    /**
     * Returns a formatted string representation of the event.
     *
     * @return A formatted string describing the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a", Locale.ENGLISH);
        String fromFormatted = from.format(formatter);
        String toFormatted = to.format(formatter);
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }
}
