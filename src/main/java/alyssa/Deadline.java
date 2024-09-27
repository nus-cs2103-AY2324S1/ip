package alyssa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a task with an end date/time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * alyssa.Deadline constructor method.
     * @param description A description for the deadline task.
     * @param by When the deadline should be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    /**
     * Returns the deadline's by in a different format: e.g. Jun 05 2001.
     */
    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
    /**
     * Returns the deadline's by in the format: 2001-06-05.
     */
    public String getByForStorage() {
        return by.toString();
    }
}
