package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Creates a Deadline object.
     * @param description The description of the deadline.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a Deadline object.
     * @return The deadline of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the deadline of the task that is to be saved by Storage.
     * @return The deadline of the task that is to be saved by Storage.
     */
    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D|" + super.toSaveString() + "|" + by.format(formatter);
    }
}
