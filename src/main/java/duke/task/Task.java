package duke.task;

/**
 * A general task that has description and status whether it is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to build a task with description as input.
     * @param description Describes the task.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Constructor to build a task with description and isDone as input.
     * @param description Describes the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * It gets the status icon of whether the task is done or not.
     * @return "X" to indicate it is done and " " to indicate it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean containsString(String string) {
        return this.description.contains(string);
    }

    /**
     * Prints out the description of the task and its status.
     * @return A string that shows the task's description and status.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
