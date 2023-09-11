package duke;

/**
 * The duke.Task class represents a basic task with a description and status to determine if task is done.
 * Tasks can be marked as done or undone.
 */
public class Task {
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
     * A method that marks the task as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * A method that marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * An accessor method to retrieve the status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * An accessor method to retrieve the description of the task.
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

}
