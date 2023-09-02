package fishron;

/**
 * Represents a task with a description and status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description and marks it as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Converts the task to a formatted string for saving to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Gets the description of the task.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets an icon representing the status of the task.
     *
     * @return A string containing an icon ([X] for done, [ ] for undone).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon().trim() + " " + getDescription().trim();
    }
}
