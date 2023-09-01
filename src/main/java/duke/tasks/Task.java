package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the icon to use for the status.
     * 
     * @return the icon to use for the status or blank if task is not done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the status of the task as done or not done.
     * 
     * @param isDone true if done, false otherwise.
     */
    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of this Task. The string is formatted as 
     * "[ ][StatusIcon] Description"
     * 
     * @return a string representation of this Task.
     */
    public String toString() {
        return String.format("[ ][%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns a string representation of this Task. The format is # Doneness #
     * Description.
     * Note that this is different from toString() as it is used for encoding data
     * in the file.
     * 
     * 
     * @return a string representation of this Task for storage in the file.
     */
    public String toFileString() {
        return String.format("  # %d # %s", (isDone ? 1 : 0), description);
    }
}
