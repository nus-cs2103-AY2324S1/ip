package duke.task;

/**
 * The Task class represents a task template in the Duke application.
 * It includes a description and a boolean flag indicating whether the task is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description and done status.
     *
     * @param description The description of the task.
     * @param isDone      The status indicating whether the task is done (true) or not (false).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, or a space " " if the task is not done.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone flag to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon.
     *
     * @return A string representation of the status of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]";
    }

    /**
     * Returns a string representation of the task for saving to a file.
     *
     * @return A formatted string containing task type, status, and description.
     */
    public String toFileString() {
        return String.format("%s | %d | %s", this.getTaskType(), this.isDone ? 1 : 0, this.description);
    }

    /**
     * Gets the task type, which should be overridden in subclasses.
     *
     * @return The task type as a string.
     */
    public String getTaskType() {
        return "";
    }
}
