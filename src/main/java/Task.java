/**
 * The Task class represents a task with a description and a status indicating whether it's done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description. By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. The icon is "X" if the task is done, or a space if it's not done.
     *
     * @return The status icon of the task.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting its status to false.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
}
