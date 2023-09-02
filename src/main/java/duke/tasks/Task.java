package duke.tasks;

/**
 * Represents a task with a description and completion status.
 * This class provides basic functionality for managing tasks.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * Indicates whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * Constructs a duke.tasks.Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, or a space if it's not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the duke.tasks.Task object.
     *
     * @return A formatted string including the completion status icon and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}