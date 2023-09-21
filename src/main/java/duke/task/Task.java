package duke.task;

/**
 * The Task class represents a task with a description and a completion status.
 * Tasks can be marked as done or undone.
 */
public abstract class Task {
    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The completion status of the task.
     */
    private boolean isDone;

    /**
     * The tag of the task.
     */
    private String tag;

    /**
     * Constructs a new Task object with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone, String tag) {
        this.description = description;
        this.isDone = isDone;
        this.tag = tag;
    }

    /**
     * Constructs a new Task object with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Gets the status of the task.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
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
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Sets a new tag.
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string indicating the task's completion status and description.
     */
    @Override
    public String toString() {
        String str = "[" + getStatusIcon() + "] " + this.description;
        return this.tag.isEmpty() ? str : str + " #" + this.tag;
    }

    /**
     * Returns a formatted string for writing the task to a file.
     *
     * @return A formatted string containing task details for file storage.
     */
    public String writeFile() {
        String str = this.description + " | " + this.tag;
        return this.isDone ? "1 | " + str : "0 | " + str;
    }
}
