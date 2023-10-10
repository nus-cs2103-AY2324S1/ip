package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event.
 */
public class Event extends Task {
    /**
     * The date of the event.
     */
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates an event with the given description and date.
     * @param description The description of the event.
     * @param startDateTime The startDateTime of the event.
     * @param endDateTime The endDateTime of the event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        assert description != null && !description.trim().isEmpty() : "description should not be null";
        super.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the string representation of the event.
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        String newStartDateTime = startDateTime.format(super.formatter);
        String newEndDateTime = endDateTime.format(super.formatter);
        return "[E]" + super.toString() + " (from: " + newStartDateTime + " to: " + newEndDateTime + ")";
    }

}
