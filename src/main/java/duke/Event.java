package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, start time, and end time.
 * Extends the Task class and includes methods for creating and displaying event tasks.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Creates a new event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param start       The start time of the event in "yyyy-MM-dd HHmm" format.
     * @param end         The end time of the event in "yyyy-MM-dd HHmm" format.
     * @throws DukeException If the description, start time, or end time is empty.
     */
    public Event(String description, String start, String end) throws DukeException {
        super(description, TaskType.EVENT);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);

        if (description.trim().isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (start.trim().isEmpty() || end.trim().isEmpty()) {
            throw new DukeException("The start or end time of an event cannot be empty.");
        }
    }

    /**
     * Returns a string representation of the event task, including its type, description,
     * start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
