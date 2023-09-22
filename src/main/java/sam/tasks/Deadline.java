package sam.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline in the Task list.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"))
                + ")";
    }

    @Override
    public String toFileString() {
        return "D " + super.toFileString() + " | " + this.by;
    }
}
