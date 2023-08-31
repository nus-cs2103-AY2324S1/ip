package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a specific deadline.
 * Inherits from the Task class.
 */
public class Deadline extends Task{
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate by;
    /**
     * Constructs a Deadline object with the provided description and deadline date.
     *
     * @param description The description of the task.
     * @param by The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    public LocalDate getDeadline() {
        return this.by;
    }

    /**
     * Converts the Deadline object to a formatted string representation.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "]"
                + super.toString() + " (by: " + by.format(DATE_FORMATTER) + ")";
    }
}
