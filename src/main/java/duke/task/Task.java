package duke.task;

/**
 * Represents a task that can be added to a task list.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a `Task` object with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return "X" if the task is done, " " (space) if the task is not done.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public String markTask() {
        this.isDone = true;
        return "Heyyo! I've marked this task as done!\n" + this;
    }

    /**
     * Unmarks the task as done.
     */
    public String unmarkTask() {
        this.isDone = false;
        return "Aww snap! I've unmarked this task!\n" + this;
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
     * Returns a string representation of the `Task` for display.
     *
     * @return A string representation of the task in the format "[X] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + description;
    }

    /**
     * Converts the task to a string for saving to the data file.
     *
     * @return A string representation of the task for saving.
     */
    public abstract String toSave();
}
