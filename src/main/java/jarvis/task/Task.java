package jarvis.task;

/**
 * Represents a generic task.
 *
 * This class serves as a parent for more specific types of tasks such as
 * {@code Todo}, {@code Deadline}, and {@code Event}.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and completion status.
     *
     * @param description The description or name of the task.
     * @param isDone Indicates if the task has been completed.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if the task is completed, false otherwise.
     */
    public Boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     * A completed task is represented by "X", and an incomplete task is represented by a space.
     *
     * @return A string representing the status icon ("X" or " ").
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Converts the Task object to a string format suitable for saving to a file.
     *
     * @return A string representation of the Task object for saving purposes.
     */
    public String toSaveString() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Returns a string representation of the Task object.
     * The representation includes the status icon and the description of the task.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
