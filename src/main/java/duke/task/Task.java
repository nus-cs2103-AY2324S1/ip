package duke.task;

/**
 * Represents a task with a description that can be marked done or undone.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a duke.task.Task instance.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, where 'X' represents that the task
     * is done, and ' ' otherwise.
     *
     * @return A string showing the done status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Provides the string representation of the duke.task.Task instance.
     *
     * @return A string with the relevant information of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Get a formatted string of the duke.task.Task to add to the save file.
     *
     * @return A formatted string with the relevant information for the save file.
     */
    public abstract String getSaveString();
}
