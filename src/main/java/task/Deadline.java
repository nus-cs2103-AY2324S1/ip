package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task which is a specific type of task
 * that has a deadline by which it must be completed.
 */
public class Deadline extends Task {

    /** The deadline time of the task. */
    protected LocalDateTime by;

    /**
     * Creates a new Deadline task with the provided description and deadline time.
     *
     * @param description The description or details of the task.
     * @param by The deadline time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super("DEADLINE", description);
        this.by = by;
    }

    /**
     * Retrieves the deadline time of the task.
     *
     * @return The deadline time of the task.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Converts the Deadline task details into a human-readable string.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        String tempString = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return "[D] " + super.toString() + " (by: " + tempString + ")";
    }

    /**
     * Converts the Deadline task details into a string format suitable for file storage.
     *
     * @return A string representation of the Deadline task for file storage.
     */
    @Override
    public String toFileString() {
        return TaskType.DEADLINE.toString() + " | " + (isCompleted() ? "1" : "0") + " | " + getDescription() + " | " + getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
