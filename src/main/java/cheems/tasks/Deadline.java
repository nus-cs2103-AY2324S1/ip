package cheems.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A child class of the abstract Task class.
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDate by;
    private final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Formats the deadline task in a user-friendly readable string.
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DATE_PATTERN) + ")";
    }

    @Override
    public String toStorage() {
        return String.format("%d|DEADLINE|%s|%s", isDone ? 1 : 0, getDescription(), by);
    }
}

