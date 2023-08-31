package duke.task;

import java.time.LocalDate;

/**
 * Represents an event with a given start and end date.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        String duration = "(from: " + this.from + " to: " + this.to + ")";
        return "[E]" + status + " " + this.description + " " + duration;
    }
}
