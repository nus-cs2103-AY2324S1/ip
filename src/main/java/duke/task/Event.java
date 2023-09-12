package duke.task;

import java.time.LocalDate;

/**
 * Represents an event with a given start and end date.
 */
public class Event extends Task {
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructor for Event class.
     * @param description The description for the Event.
     * @param fromDate The start date for the Event.
     * @param toDate The end date for the Event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;

        assert this.fromDate != null : "fromDate of the Event cannot be null";
        assert this.toDate != null : "toDate of the Event cannot be null";
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        String duration = "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return "[E]" + status + " " + this.description + " " + duration;
    }
}
