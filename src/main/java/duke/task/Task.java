package duke.task;

/**
 * Represents a task.
 *
 * @author Pearlynn
 */

public abstract class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task. */
    protected boolean isDone;

    /**
     * Constructor for duke.task.Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for duke.task.Task class.
     *
     * @param description The description of the task.
     * @param isDone The status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return "X" to mark the task as done, or " " if otherwise.
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
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task in the file.
     *
     * @return A string representation of the task in the file.
     */
    public abstract String taskStringify();

    /**
     * Returns the string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
