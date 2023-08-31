package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task. A <code>Event</code> corresponds to a Task
 * which has a description, from date, to date, and stores information whether this task
 * has been completed.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private DateTimeFormatter formatterSave = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs an Event task which contains a description, a start time and
     * an end time.
     *
     * @param description Event name.
     * @param start Start time for the Event.
     * @param end End time for the Event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs an Event task which contains a description, a start time,
     * an end time, and whether the Event is completed.
     *
     * @param description Event name.
     * @param start Start time for the Event.
     * @param end End time for the Event.
     * @param isDone {@code true} if the task is marked as done, {@code false} otherwise.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns {@code true} if the start date is before or same as the end date, and
     * returns {@code false} otherwise.
     *
     * @param start Start date to be compared.
     * @param end End date to be compared.
     */
    public boolean isStartDateBefore (LocalDateTime start, LocalDateTime end) {
        if (start.isBefore(end) || start.equals(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + start.format(formatterSave)
                + " | " + end.format(formatterSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() +
                " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}