package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents an event task with a specific start and end time.
 * Inherits from the Task class.
 */
public class Event extends Task {
    static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    LocalDateTime from, to;

    /**
     * Constructs an Event object with the provided description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event object to a formatted string representation.
     *
     * @return The string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "]"
                + super.toString() + " (from: " + from.format(DATE_TIME_FORMATTER) +
                " to: " + to.format(DATE_TIME_FORMATTER) + ")";
    }
}
