package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that starts and ends at specific times.
 * Inherits from the Task class.
 */
public class Event extends Task {

    /** The start time of the event */
    protected LocalDateTime from;

    /** The end time of the event */
    protected LocalDateTime to;

    /**
     * Constructs an Event object with the given description, start time, and end time.
     *
     * @param description The description of the task.
     * @param from The start time of the task in the format "yyyy-MM-dd HH:mm".
     * @param to The end time of the task in the format "yyyy-MM-dd HH:mm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns a formatted string representation of the Event object.
     *
     * @return A formatted string representation of the Event object.
     */
    @Override
    public String toFormattedString() {
        return "E | " + (isDone ? "1" : "0") + " | "
                + description + " | " + from + " | " + to;
    }
}
