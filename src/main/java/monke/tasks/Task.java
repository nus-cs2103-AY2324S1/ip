package monke.tasks;

/**
 * The Task class represents a task in the Monke application.
 * It is an abstract class and serves as the base class for specific task types.
 */
public abstract class Task {
    /** The task description */
    protected String description;

    /** Indicates whether the task is done. */
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon indicating the status of the task.
     *
     * @return "X" if the task is done, " " (whitespace) if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Formats the task data for saving to a file.
     *
     * @return A formatted string representing the task data.
     */
    public abstract String formatData();
}