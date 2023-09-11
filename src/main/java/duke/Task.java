package duke;

/**
 * Represents a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a task
     *
     * @param description the task details
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        assert this.description != null : "description should not be null";
    }

    /**
     * Check if the task has been completed and
     * return the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Mark a task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Mark a task as not done
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Return the string representation of the task.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Return the string representation of the task in file format.
     *
     * @return the string representation in file format
     */
    abstract String toFileFormat();

    /**
     * Return the type of the task.
     *
     * @return the type of the task
     */
    abstract String getTaskType();
}
