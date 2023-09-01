package sisyphus.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor when provided description, isDone will be set to false by default in this case.
     *
     * @param description
     */
     public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor when provided description and isDone.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns status icon of the Task.
     *
     * @return "X" if task is Done and " " if Task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Set isDone to the given state.
     *
     * @param state
     */
    public void setDone(boolean state) {
        this.isDone = state;
    }

    /**
     * Returns formatted Task string.
     *
     * @return formatted string with status icon and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " +  this.description;
    }

    public abstract String toSaveFormat();
}
