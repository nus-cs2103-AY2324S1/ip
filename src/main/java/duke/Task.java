package duke;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected static int total = 0;

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        Task.total += 1;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Converts the task to a formatted string.
     *
     * @return The formatted string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description; // mark done task with X
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
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Gets the total number of tasks.
     *
     * @return The total number of tasks.
     */
    public static int getTotal() {
        return Task.total;
    }

    /**
     * Removes the task.
     */
    public void remove() {
        Task.total -= 1;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return The formatted string for saving the task.
     */
    public String toSaveString() {
        return "";
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
}
