package grumpygordon.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a grumpygordon.tasks.Deadline object.
 */
public class Deadline extends Task {
    /**
     * End time of deadline.
     */
    protected LocalDateTime by;

    /**
     * Constructor to create a grumpygordon.tasks.Deadline object.
     * @param description Description of event
     * @param by End time of event
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns a String representation of a grumpygordon.tasks.Deadline.
     * @return String representation of a grumpygordon.tasks.Deadline.
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (this.isDone ? "1" : "0") + " | " + super.toString() + " | " + this.by;
    }
}