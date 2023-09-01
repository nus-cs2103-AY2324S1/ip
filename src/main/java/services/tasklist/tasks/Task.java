package services.tasklist.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;
    /** The status of task completion. */
    protected boolean isDone;
    /** The checkbox to indicate the status of task completion. */
    protected String checkBox;

    /**
     * Constructor for Task.
     * The task is not done by default.
     *
     * @param description the content of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.checkBox = "[ ]";
    }

    public void setDone() {
        this.isDone = true;
        this.checkBox = "[X]";
    }

    public void setUndone() {
        this.isDone = false;
        this.checkBox = "[ ]";
    }

    /**
     * Encodes the task into a string that can be saved to a data file.
     *
     * @return the encoded string.
     */
    public abstract String encode();

    @Override
    public String toString() {
        return checkBox + " " + this.description;
    }
}
