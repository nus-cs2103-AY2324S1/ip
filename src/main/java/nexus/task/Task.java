package nexus.task;

/**
 * Parent class Task for the different tasks to be stored.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create task from description.
     *
     * @param description String
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Getter to check if task is done.
     *
     * @return String to represent if task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done Nexus.task with X
    }

    /**
     * Getter for task description.
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Abstract method to convert Task to store on local storage.
     *
     * @return String to be stored on hard disk.
     */
    public abstract String toStorageString();
}
