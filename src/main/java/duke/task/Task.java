package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task in the task list.
 */
public abstract class Task {

    /**
     * The description of the duke.task.
     */
    protected String description;

    /**
     * Whether the duke.task is done.
     */
    protected boolean isDone;

    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Returns the status icon of the duke.task.
     * @return The status icon of the duke.task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the duke.task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the duke.task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the duke.task.
     * @return The description of the duke.task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
