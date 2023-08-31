package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task that starts and ends at specific date and time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event task with the given description, start time, end time, and completion status.
     *
     * @param description The description of the task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @param isDone The completion status of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event task to its string representation.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.localDateTimeToString(from) + " to: " +
                super.localDateTimeToString(to) + ")";
    }

    /**
     * Converts the Event task to its file format representation.
     *
     * @return The file format representation of the Event task.
     */
    public String toFile() {
        return "E" + super.toFile() + this.from + "--" + this.to;
    }
}
