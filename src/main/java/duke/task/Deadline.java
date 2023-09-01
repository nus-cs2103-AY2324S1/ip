package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a specific deadline.
 * Inherits properties and methods from the Task class.
 */
public class Deadline extends Task {

    /**
     * The deadline for the task.
     */
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type, completion status, description, and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by:" + super.localDateTimeToString(by) + ")";
    }
}
