package duke;

import java.io.Serializable;

/**
 * Represents a generic task.
 * This class implements Serializable to allow tasks to be serialized and deserialized,
 * enabling them to be stored and retrieved from external sources, such as files.
 */
public abstract class Task implements Serializable {
    private String name;
    private boolean isDone;

    /**
     * Constructs a task with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return {@code true} if the task is done, {@code false} otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Generates a formatted string representation of the task.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "["
            + (
            isDone ? "X" : " ")
            +
            "] "
            +
            this.name;
    }
}
