package simon.task;

import java.util.Objects;

/**
 * The {@code Task} class represents a general task with a description and a completion status.
 * This class serves as the base class for other specialized task types.
 */
public class Task {

    /** The name or description of the task. */
    private final String taskName;

    /** Flag indicating whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructs a new Task with the given name or description.
     *
     * @param taskName The name or description of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(taskName, task.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName);
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
