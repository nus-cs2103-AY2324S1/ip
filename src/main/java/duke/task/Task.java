package duke.task;

/**
 * The duke.task.Task class represents a basic duke.task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new duke.task.Task with the provided description.
     *
     * @param description The description of the duke.task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the duke.task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the duke.task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns a string representing the completion status of the duke.task.
     *
     * @return "X" if the duke.task is done, " " (a space) if the duke.task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String toFileString() {
        return "";
    }

    /**
     * Returns a string representation of the duke.task.
     *
     * @return A formatted string including the duke.task's completion status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
