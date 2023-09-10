package duke.tasks;

/**
 * Represents a task with a specific deadline.
 *
 * <p> A Deadline task is an extension of the basic Task, with an added
 * characteristic of a "by" field that represents the deadline of the task. </p>
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(TaskType.DEADLINE, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A formatted string showing the deadline task's type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.by + ")";
    }

    /**
     * Returns a transformed format of the deadline task, useful for data storage or other operations.
     *
     * @return A string that represents the deadline task in a unique format.
     */
    @Override
    public String transformFormat() {
        return super.transformFormat() + " | " + this.by;
    }

    /**
     * Returns the deadline of this task.
     *
     * @return A {@code String} representing the deadline of the task.
     */
    public String getBy() {
        return this.by;
    }
    /**
     * Modifies the by of this task.
     */
    public void setBy(String newBy) {
        this.by = newBy;
    }
}
