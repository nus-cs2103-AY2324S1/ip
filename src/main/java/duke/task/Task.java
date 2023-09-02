package duke.task;

/**
 * Represents a task that has a deadline.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a Task object.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     * @return The description of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the task that is to be saved by Storage.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the description of the task that is to be saved by Storage.
     * @return The description of the task that is to be saved by Storage.
     */
    public String toSaveString() {
        return String.format("%s|%s", isDone ? "1" : "0", description);
    }

}
