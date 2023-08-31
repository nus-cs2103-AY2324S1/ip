package duke.task;

/**
 * Represents an abstract task in the Duke application.
 * This class provides common functionality and structure for various types of tasks.
 */
public abstract class Task {
    private final String taskName;
    private boolean isDone;

    /**
     * Creates a new task with the given name and sets its completion status to false.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Creates a new task with the given name and completion status.
     *
     * @param taskName The name or description of the task.
     * @param isDone   The completion status of the task.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return A string representation indicating whether the task is done or not.
     */
    public String getTaskStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Retrieves the name or description of the task.
     *
     * @return The name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Retrieves the type of the task (e.g., "ToDo", "Event").
     * Must be implemented by subclasses.
     *
     * @return The type of the task.
     */
    public abstract String getTaskType();

    /**
     * Retrieves the time associated with the task (if any).
     * Must be implemented by subclasses.
     *
     * @return A string representation of the task's time, or an empty string if not applicable.
     */
    public abstract String getTaskTime();

    /**
     * Formats the task into a string suitable for saving to storage.
     * Must be implemented by subclasses.
     *
     * @return The task formatted as a string.
     */
    public abstract String toSaveFormat();

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Provides a string representation of the task, including its type, status, name, and time.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return this.getTaskType() + this.getTaskStatus() + " " + this.getTaskName() + this.getTaskTime();
    }
}
