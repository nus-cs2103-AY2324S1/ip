package duke.task;

/**
 * The duke.task.Task class represents a generic duke.task with a description and a completion status.
 * It serves as the base class for specific duke.task types.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new duke.task.Task instance with a given description.
     *
     * @param description The description of the duke.task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the duke.task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the duke.task as not completed (undone).
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Gets the status icon of the duke.task, indicating completion.
     *
     * @return "X" if the duke.task is done, " " (space) if the duke.task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the duke.task for saving to a file.
     *
     * @return An empty string; specific duke.task types override this method.
     */
    public String toTxtString() {
        return "";
    }

    /**
     * Returns a string representation of the duke.task for displaying to the user.
     *
     * @return A string in the format "[Status] Description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
