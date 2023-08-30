package cheems.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A child class of the abstract Task class.
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        try {
            LocalDate date = LocalDate.parse(by);
            this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            this.by = by;
        }
    }

    /**
     * Formats the deadline task in a user-friendly readable string.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStorage() {
        return String.format("%d|DEADLINE|%s|%s", this.isDone ? 1 : 0, this.getDescription(), this.by);
    }
}

