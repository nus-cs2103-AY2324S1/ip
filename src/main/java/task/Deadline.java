package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * @author Your Name
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline date
     * and time.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date and time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Retrieves the deadline date and time of the task.
     *
     * @return The deadline date and time.
     */
    public LocalDateTime getCompleteBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string in the format: "[D][Status] Description (by: dd MMM yyyy,
     *         HHmm)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, HHmm")) + ")";
    }
}
