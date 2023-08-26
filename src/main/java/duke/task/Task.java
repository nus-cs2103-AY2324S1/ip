package duke.task;

/**
 * Represents a task in the Duke application.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the status icon of the task.
     *
     * @return A string containing "[X]" if the task is done, or "[ ]" if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
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
     * Returns a formatted string representation of the task.
     *
     * @return A formatted string displaying the task's details.
     */
    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }

    /**
     * Generates a formatted string representation of the task for saving.
     *
     * @return A formatted string suitable for saving in a data file.
     */
    public String toSaveLine() {
        return ("");
    }
}
