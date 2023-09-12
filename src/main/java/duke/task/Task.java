package duke.task;

/**
 * Represents a generic task with a description and completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a task with the given description and sets the completion status to false.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a task with the given description and completion status.
     *
     * @param description Description of the task.
     * @param isDone Completion status of the task.
     */
    public Task(String description, boolean isDone) {
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " (space) if not done.
     */
    public String getStatusIcon() {
        String statusIcon = (this.isDone ? "X" : " ");
        assert "X".equals(statusIcon) || " ".equals(statusIcon) : "Invalid status icon";
        return statusIcon; // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return (this.description);
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * example: [T][X] return book
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
}
