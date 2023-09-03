package duke.tasks;

/**
 * Represents a task object.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given task description.
     *
     * @param description Describes task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string to indicate whether task is done.
     *
     * @return status of whether task is done in "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Represents task in string format.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Represents task in string format to be stored.
     *
     * @return String representation of task.
     */
    public String toStorageFormat() {
        return (" | " + (isDone ? "1" : "0") + " | " + this.description);
    }

}
