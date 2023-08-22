/**
 * The Task class represents a task with a description and a completion status.
 * Tasks can be marked as done or undone.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
