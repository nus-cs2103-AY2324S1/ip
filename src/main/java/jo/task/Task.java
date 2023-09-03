package jo.task;

/**
 * Represents a generic task in the `Jo` application.
 * It includes properties such as a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new `Task` object with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      `true` if the task is marked as done, `false` if it is undone.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon ("X" if the task is done, " " if it is undone).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return A string in the format: "[T][Status] Description".
     */
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Marks the task as done or undone.
     *
     * @param isDone `true` to mark the task as done, `false` to mark it as undone.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return `true` if the task is marked as done, `false` if it is undone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Converts the task to a string format suitable for storing in a file.
     *
     * @return A string in the format: "T | Status | Description".
     */
    public String toFile() {
        return String.format("T | %s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() { return this.description; }
}
