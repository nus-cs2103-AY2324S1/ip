package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with a description, start time, and end time. Event tasks are tasks that occur within a
 * specified time frame.
 */
public class Event extends Task {

    /**
     * Start time of the event.
     */
    protected LocalDateTime from;

    /**
     * End time of the event.
     */
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description Description of the event.
     * @param from        Start time of the event.
     * @param to          End time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return Formatted string showing the event's type, completion status, description, and start and end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatLocalDateTime(from) + " to: " + formatLocalDateTime(to)
                + ")";
    }

    /**
     * Serializes the event task to a string format for saving.
     *
     * @return Serialized string representation of the event task.
     */
    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, serializeLocalDateTime(from),
                serializeLocalDateTime(to));
    }
}
