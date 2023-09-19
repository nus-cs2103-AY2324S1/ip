package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Initializes an event task with the given description and date in String.
     *
     * @param description Description of the event task.
     * @param from        Starting date of the event task.
     * @param to          Ending date of the event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";

        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Initializes an event task with the given description and date in LocalDate.
     *
     * @param description Description of the event task.
     * @param from        Starting date of the event task.
     * @param to          Ending date of the event task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert from != null : "Starting date should not be null";
        assert to != null : "Ending date should not be null";
        assert !from.isAfter(to) : "Start date should not be after end date";
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event task.
     * The string representation is formatted as follows:
     * example: [E][X] project meeting (at: Aug 6 2021)
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
