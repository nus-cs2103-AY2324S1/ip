package duke;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and marks it as incomplete.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean idDone() {
        return this.isDone;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is done, " " (space) if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void finish() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unfinish() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string with the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
