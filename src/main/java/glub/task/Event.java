package glub.task;

import java.time.LocalDateTime;

/**
 * Abstraction of an event that the user can create.
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor for the event with tag.
     * @param task Task description.
     * @param isDone Task status.
     * @param tag Task tag.
     * @param start Start of event.
     * @param end End of event.
     */
    public Event(String task, boolean isDone, String tag, LocalDateTime start, LocalDateTime end) {
        super(task, isDone, tag);
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
                + String.format("(from: %s to: %s) %s",
                super.getDisplayDateTime(start), super.getDisplayDateTime(end), super.getTag());
    }
}
