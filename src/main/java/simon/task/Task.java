package simon.task;

/**
 * The {@code Task} class represents a general task with a description and a completion status.
 * This class serves as the base class for other specialized task types.
 */
public class Task {

    /** The name or description of the task. */
    public String taskName;

    /** Flag indicating whether the task has been completed. */
    public boolean isDone;

    /**
     * Constructs a new Task with the given name or description.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
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
