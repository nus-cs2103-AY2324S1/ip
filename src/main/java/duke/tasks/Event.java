package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, a subclass of Task.
 * An Event task has a description and a specific start and end date/time.
 *
 * <p>Each Event task can be stored or printed in specific formats as defined by its constants.</p>
 */
public class Event extends Task {

    /** Format to print an Event task to the user. */
    private static final String PRINT_FORMAT = "[E]%s %s (from: %s to: %s)";

    /** Format to store an Event task in storage. */
    private static final String STORE_FORMAT = "[E] | %s | %s | %s | %s";

    /** The starting datetime of the event. */
    private final LocalDateTime start;

    /** The ending datetime of the event. */
    private final LocalDateTime end;

    /**
     * Initializes an Event task with the provided description, start, and end date/times.
     *
     * @param description The description of the Event task.
     * @param from The starting date/time of the event.
     * @param to The ending date/time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.start = from;
        this.end = to;
    }

    /**
     * Returns the starting datetime of the event.
     *
     * @return The starting datetime of the event.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the ending datetime of the event.
     *
     * @return The ending datetime of the event.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Returns the string representation of this Event task in a format suitable for storage.
     *
     * @return The storage-friendly string representation of this Event task.
     */
    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), start, end);
    }

    /**
     * Returns the string representation of this Event task.
     *
     * @return The string representation of this Event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(),
                start.format(dtFormat), end.format(dtFormat));
    }
}
