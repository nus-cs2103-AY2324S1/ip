package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 * An event task is a type of task that has a description and a specified start and end date and time.
 * It is used in a task management system to keep track of events with specific durations.
 */
public class Event extends Task {

    /** The start date and time of the event. */
    protected LocalDateTime start;

    /** The end date and time of the event. */
    protected LocalDateTime end;

    /**
     * Creates a new Event task with the given description, start, and end date and time.
     *
     * @param description A description of the event.
     * @param start       The start date and time of the event.
     * @param end         The end date and time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string in the format: "[E] [Task Description] (from: [Start Date and Time] to: [End Date and Time])".
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}
