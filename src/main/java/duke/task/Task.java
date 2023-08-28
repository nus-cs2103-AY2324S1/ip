package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns symbol to indicate whether task is done.
     *
     * @return X if the task is done else returns space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }


    /**
     * Marks tasks as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Returns the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether task is done.
     *
     * @return true if task is done else false.
     */
    public boolean getIsDone() {
        return this.isDone;
    }
}
