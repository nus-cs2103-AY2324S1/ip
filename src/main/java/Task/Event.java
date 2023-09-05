package task;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Event class is used for tasks that are held specific date and time.
 */
public class Event extends Task{
    /**
     * The From.
     */
    protected LocalDateTime from;
    /**
     * The To.
     */
    protected LocalTime to;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public Event(String description, LocalDateTime from, LocalTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon() + "(from: " + from + " to: " + to + ")" ;
    }

    @Override
    public String toString() {
        return String.format("E | %d | %s | %s | %s", super.isDone ? 1 : 0, super.description, this.from, this.to);
    }
}
