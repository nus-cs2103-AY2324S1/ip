package glub.task;

import java.time.LocalDateTime;

/**
 * Abstraction of an event that the user can create.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for the event.
     * @param task Task description.
     * @param isDone Task status.
     * @param start Start of event.
     * @param end End of event.
     */
    public Event(String task, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(task, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toSaveFormat() {
        return "E|" + super.toSaveFormat()
                + String.format("|%s|%s\n", super.getSaveDateTime(start), super.getSaveDateTime(end));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format("(from: %s to: %s)", super.getDisplayDateTime(start), super.getDisplayDateTime(end));
    }
}
