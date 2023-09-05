package duke.tasks;

/**
 * Represents a generic task containing the description and own task state.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    protected Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Encodes a Task into the string representation to save into storage.
     *
     * @return The string representing a task for storage.
     */
    public abstract String encode();

    /**
     * Gets the appropriate status icon string for the task.
     *
     * @return The status icon for the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a task.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}