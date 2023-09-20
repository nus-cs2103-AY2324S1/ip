package bee;

/**
 * Represents a basic task with a description and completion status.
 * Provides methods for managing task properties and retrieving information.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the Task to a formatted string representation.
     *
     * @return Formatted string representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
