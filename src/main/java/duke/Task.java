package duke;

/**
 * The duke.Task class represents a basic task with a description and status to determine if task is done.
 * Tasks can be marked as done or undone.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the duke.Task class
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Retrieves the status of the task to load into file.
     */
    public String getFileStatusIcon() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    /**
     * Retrieves the description of the task.
     *
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Overrides toString method from Object
     * returns a String representation of the task.
     *
     * @return The String representation of the task
     */
    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.description);
    }

    /**
     * Returns a String repreentation of tasks in file format
     *
     * @return The String representation of the task to save in a file.
     */
    public String toFileFormat() {
        return (" | " + getFileStatusIcon() + " | " + this.description);
    }

    /**
     * Checks if the input task is equals to the current Task instance.
     *
     * @param newTask The task to be compared to
     * @return True if the both task are the same
     */
    public abstract boolean isDuplicate(Task newTask);
}
