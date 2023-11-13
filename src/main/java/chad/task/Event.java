package chad.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs a new Event instance.
     * @param description the description of the task.
     * @param start the start time for the event.
     * @param end the end time for the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task.
     * @return the string format of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");
        return String.format("[E][%s] %s (from: %s to: %s)", super.getStatusIcon(),
                description, start.format(formatter), end.format(formatter));
    }

    /**
     * Returns the event task in a format suitable for file storage.
     * @param formatter the DateTimeFormatter to use for the start and end times.
     * @return the string format of the event task for file storage.
     */
    @Override
    public String toFileFormat(DateTimeFormatter formatter) {
        return String.format("E | %s | %s | %s | %s", super.isDoneString(), description, start.format(formatter),
                end.format(formatter));
    }

    /**
     * Gets the start time of the event task.
     * @return the start time as a LocalDateTime object.
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Gets the end time of the event task.
     * @return the end time as a LocalDateTime object.
     */
    public LocalDateTime getEnd() {
        return end;
    }
}
