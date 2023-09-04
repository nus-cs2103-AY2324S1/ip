package duke.tasks;

/**
 * Encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon associated with whether a task is done or not.
     *
     * @return The status icon associated with whether a task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task for storage in duke.txt.
     *
     * @return The string representation of the task for storage in duke.txt..
     */
    public String toStorageString() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
