package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task that has a LocalDate it us due by.
 */
public class Deadline extends Task {
    /** A LocalDate which the Deadline is due by. */
    protected LocalDate by;

    /**
     * Constructs a Deadline with a description and a LocalDate it is due by.
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the Deadline.
     * @return a String representation
     */
    @Override
    public String toString() {
        String date = by.format(DateTimeFormatter.ofPattern("MMM d YYYY"));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    /**
     * Returns a String representation of the Deadline for the .txt file.
     * @return a String representation
     */
    @Override
    public String toFileString() {
        int bool = this.isDone ? 1 : 0;
        return "D | " + bool + " | " + this.description + " | " + by + "\n";
    }
}
