package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a deadline task with description and due date.
     *
     * @param description Description of the deadline.
     * @param by Due date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return Due date.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Converts the deadline task into a string.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
