package duke.data.task;

/**
 * Represents a task that the user wishes to keep track of.
 * A task object has a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task object
     *
     * @param description The description of a given task
     */
    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    /**
     * Returns the description of a given task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a description for the task
     *
     * @param description The description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the completion status of the task
     *
     * @param done Completion status
     */

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the status icon of the task.
     * If the task is completed, an "X" is shown. Otherwise, the icon is empty.
     *
     * @return statusIcon
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    /**
     * Mark the task as complete.
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Mark the task as incomplete.
     */
    public void markAsNotDone() {
        setDone(false);
    }

    /**
     * Generates an encoded string containing metadata of the task to be saved in a text file.
     *
     * @return Encoded string representation of the task
     */
    public abstract String toSaveDataFormat();

    /**
     * Produces a string representation of a Task object.
     *
     * @return String representation of a Task object.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}