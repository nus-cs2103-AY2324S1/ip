package echobot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an event task with description and date-time range.
     *
     * @param description Description of the event.
     * @param start Starting date and time of the event.
     * @param end Ending date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the starting date and time of the event.
     *
     * @return Starting date and time.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the ending date and time of the event.
     *
     * @return Ending date and time.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String display() {
        return "[E] " + super.display() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }
}
