package duke.data.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        setDone(false);
    }

    /**
     * Returns the description of a given task
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the completion status of a given task
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Mark completed task with "X"
     * @return statusIcon
     */
    public String getStatusIcon() {
        return (isDone() ? "X" : " ");
    }

    public void markAsDone() {
        setDone(true);
    }

    public void markAsNotDone() {
        setDone(false);
    }

    public abstract String toSaveDataFormat();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
