package duke;

/**
 * A class containing information about each task
 * They have a name, and a boolean to check
 * whether the task is done
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create task based on description
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string depending on whether the task is done
     *
     * @return X if the task is done, a space if it isn't
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Mark the task as complete/incomplete respectively
     */
    public void markAsDone() {
        isDone = true;
    }
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Get the description of the task
     *
     * @return The description of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
