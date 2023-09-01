package cheems.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A child class of the abstract Task class.
 * Represents a task that has a start time and an end time.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("MMM dd yyyy");


    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Formats the event task in a user-friendly readable string.
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + this.from +
                " to: " + this.to + ")";
    }

    @Override
    public String toStorage() {
        return String.format("%d|EVENT|%s|%s|%s", this.isDone ? 1 : 0, this.getDescription(),
                this.from.format(DATE_PATTERN), this.to.format(DATE_PATTERN));
    }
}
