package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task with a description and a due date. Deadline tasks are tasks that need to be done before a specified
 * due date.
 */
public class Deadline extends Task {

    /**
     * Due date of the deadline task.
     */
    protected LocalDateTime dueDate;

    /**
     * Constructs a Deadline object with the given description and due date.
     *
     * @param description Description of the deadline task.
     * @param dueDate     Due date of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return Formatted string showing the deadline's type, completion status, description, and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatLocalDateTime(dueDate) + ")";
    }

    /**
     * Serializes the deadline task to a string format for saving.
     *
     * @return Serialized string representation of the deadline task.
     */
    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, serializeLocalDateTime(dueDate));
    }
}
