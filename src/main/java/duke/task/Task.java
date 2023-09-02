package duke.task;

import java.io.Serializable;

/**
 * Represents a task with a description and a status (done or
 * not done).
 */
public abstract class Task implements Serializable {

    /** Represents the description of the task. */
    private String description;
    /** Represents the status of the task. */
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description and sets the status to not done.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is done, and " " (a space) if the task is not
     *         done.
     * @return The method is returning a string. If the variable "isDone" is true, it returns "X",
     *         otherwise it returns a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of an object.
     * @return The method is returning the value of the instance variable "description".
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone variable to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone variable to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of an object, including its status icon and
     *         description.
     * @return The method is returning a string representation of the object. The string consists of the
     *         status icon followed by the description of the object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
