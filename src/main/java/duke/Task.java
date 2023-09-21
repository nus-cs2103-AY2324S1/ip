package duke;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new instance of the Task class with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task. Returns 'X' if the task is done, and ' ' otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the number icon of the task. Returns '1' if the task is done, and '0' otherwise.
     *
     * @return The number icon of the task.
     */
    public String getNumberIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task with its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a formatted string representation of the task for file storage.
     *
     * @return A formatted string representation of the task.
     */
    public String toFileString() {
        return " | " + getNumberIcon() + " | " + description;
    }

}

