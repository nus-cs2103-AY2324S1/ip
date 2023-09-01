package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event task with a start and end time.
 * Inherits properties and methods from the Task class.
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
     * Constructs an Event object with the given description, start time, and end time.
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
     * Returns a string representation of the Event task, including its type, completion status, description, start time, and end time.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
