package simon.task;

/**
 * Represents a task with a name or description.
 * Each task can be marked as done or not done.
 */
public class Task {

    /** The name or description of the task. */
    public String taskName;

    /** Indicates whether the task is done or not. */
    public boolean isDone;

    /**
     * Constructs a new task with the specified name or description.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts this task to a string format suitable for display.
     *
     * @return A string representation of this task.
     */
    @Override
    public String toString() {
        return this.taskName;
    }
}
