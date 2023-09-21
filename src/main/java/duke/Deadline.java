package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a new Deadline task.
     *
     * @param description The description or name of the task.
     * @param by The date and time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the task for user display.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";

    }

    /**
     * Returns a string representation of the task for saving to file.
     *
     * @return A string representing the task in a format suitable for file storage.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + by;
    }
}
