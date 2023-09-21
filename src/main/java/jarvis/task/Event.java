package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 *
 * An Event is a task with additional LocalDateTime attributes to indicate its start and end times.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, false);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event object with the specified description, completion status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isDone Indicates if the task has been completed.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, Boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event as a formatted string.
     *
     * @return A string representing the formatted start time of the event.
     */
    public String getFromString() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Returns the end time of the event as a formatted string.
     *
     * @return A string representing the formatted end time of the event.
     */
    public String getToString() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }

    /**
     * Converts the Event object to a string format suitable for saving to a file.
     *
     * @return A string representation of the Event object for saving purposes.
     */
    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + getFromString() + " | " + getToString();
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}
