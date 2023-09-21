package tasket.task;

/**
 * The class for task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The constructor for task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task status in string format.
     *
     * @return X if it's done, otherwise a black space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Return the task status in save format.
     *
     * @return 1 if it's done, otherwise 0.
     */
    public String getSaveStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark the task to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task to undone
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the task in string format.
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns the task in save format.
     *
     * @return The save format of the task.
     */
    public String toSaveString() {
        return String.format("%s | %s", getSaveStatusIcon(), this.description);
    }
}
