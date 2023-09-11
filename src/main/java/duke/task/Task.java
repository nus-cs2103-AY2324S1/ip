package duke.task;

import java.io.Serializable;

/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as the base class for different types of tasks in the Duke application.
 */
public abstract class Task implements Serializable {
    private String description;
    private boolean isDone;
    private String icon = "[ ]";

    /**
     * Constructs a Task object with the specified task description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done and updates the completion status icon.
     */
    public void markAsDone() {
        this.isDone = true;
        this.icon = "[X]";
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string containing the completion status icon and the task description.
     */
    @Override
    public String toString() {
        return this.icon + " " + this.description;
    }
}
