package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the task list.
 */
public abstract class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Whether the task is done.
     */
    protected boolean isDone;

    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Returns the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
