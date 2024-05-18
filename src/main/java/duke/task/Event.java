package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates a Event object.
     * @param description The description of the event.
     * @param start The start of the event.
     * @param end The end of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates a Event object.
     * @return The event of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return "[E]" + super.toString() + " (from: " + start.format(formatter) + " to " + end.format(formatter) + ")";
    }

    /**
     * Returns the event of the task that is to be saved by Storage.
     * @return The event of the task that is to be saved by Storage.
     */
    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E|" + super.toSaveString() + "|" + start.format(formatter) + "|" + end.format(formatter);
    }
}
