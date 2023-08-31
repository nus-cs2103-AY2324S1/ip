package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that occurs within a specified time range.
 * It extends the Task class and includes details about the start and end times of the event.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs an Event object with the specified task description, start time, and end time.
     *
     * @param task The description of the event task.
     * @param start The start time of the event as a LocalDateTime object.
     * @param end The end time of the event as a LocalDateTime object.
     */
    public Event(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string containing event type, completion status, task description, start and end time.
     */
    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ", to: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")");
    }
}
