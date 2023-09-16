package sae.task;

/**
 * The Task class represents a task with a description and a status indicating whether it's done or not.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description. By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. The icon is "X" if the task is done, or a space if it's not done.
     *
     * @return The status icon of the task.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done by setting its status to false.
     */
    public void unMarkTask() {
        this.isDone = false;
    }

    /**
     * Converts the task to a formatted string for file storage.
     *
     * @return A formatted string representing the task for file storage.
     */
    public abstract String toFileString();
}

