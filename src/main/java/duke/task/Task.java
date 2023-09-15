package duke.task;

/**
 * Represents a general task.
 * This class provides the basic structure and common functionalities of a task.
 * Subclasses should define how to convert the task to a string for file storage.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Converts the task to a string representation suitable for file storage.
     *
     * @return String representation of the task for file storage.
     */
    public abstract String toFileString();
}
