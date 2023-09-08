package duke.task;

/**
 * The duke.task.Task class represents a generic task with a description and a completion status.
 * It serves as the base class for specific task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task instance with a given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed (undone).
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Gets the status icon of the task, indicating completion.
     *
     * @return "X" if the task is done, " " (space) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return String representation of description of task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return An empty string; specific task types override this method.
     */
    public String toTxtString() {
        return "";
    }

    /**
     * Returns a string representation of the task for displaying to the user.
     *
     * @return A string in the format "[Status] Description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
