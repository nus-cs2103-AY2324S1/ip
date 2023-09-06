package tasks;

/**
 * The Task class represents a basic task with a name and completion status.
 * It provides methods for retrieving the task's name, status, and a custom
 * string representation of the task, as well as for marking the task as done
 * or undone.
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified name and sets its initial
     * completion status to "not done."
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return An "X" if the task is done, or a space if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.taskName;
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task in the format:
     * [status] taskName
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }
}
