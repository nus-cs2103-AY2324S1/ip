public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * The status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a Task object.
     * @param description The description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }
    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
