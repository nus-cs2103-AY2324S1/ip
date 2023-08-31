package duke.data.task;

/**
 * Represents a task to be done.
 */
public abstract class Task {

    /** The description of the given task. */
    protected String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Returns a string representation of whether the task is done. */
    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    /** Sets the task as done. */
    public void markAsDone() {
        this.isDone = true;
    }

    /** Sets the task as not done yet. */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a string representation of the task for when it is stored.
     * @return
     */
    public String getStorageString() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }
}
