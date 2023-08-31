package duke.tasks;

import java.time.LocalDateTime;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline task with the given description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @param isDone The completion status of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Converts the Deadline task to its string representation.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.localDateTimeToString(by) + ")";
    }

    /**
     * Converts the Deadline task to its file format representation.
     *
     * @return The file format representation of the Deadline task.
     */
    public String toFile() {
        return "D" + super.toFile() + this.by;
    }
}
