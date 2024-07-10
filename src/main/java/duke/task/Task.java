package duke.task;

/**
 * The task object that stores each task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the tasks has been done
     * @return "X" if done, " " if not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task
     * @return description string
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of completion of task
     * @param status completed status
     */
    public void setDone(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    public abstract String toSave();
}
