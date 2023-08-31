package monday.task;

import java.io.Serializable;

/**
 * The Task class represents a task with a description and a completion status.
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description
     * and sets its completion status to false.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based on its completion status.
     *
     * @return "X" if the task is done, " " (empty space) otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * including its completion status icon and description.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
