package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 *
 * @author Ho Khee Wei
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the specified description, start time, and end
     * time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDateTime getStartTime() {
        return from;
    }

    /**
     * Retrieves the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDateTime getEndTime() {
        return to;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string in the format: "[E][Status] Description (from: dd MMM yyyy,
     *         HHmm to: dd MMM yyyy, HHmm)"
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")),
                to.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")));
    }
}
