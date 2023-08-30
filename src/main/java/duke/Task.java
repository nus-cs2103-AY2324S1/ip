package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A constructor for a task
     *
     * @param description the task details
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Check if the task has been completed and
     * return the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return(isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    abstract String toFileFormat();

    abstract String getTaskType();
}
