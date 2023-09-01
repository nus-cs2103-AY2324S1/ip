/**
 * Represent a task that is either done or not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task that is initially undone.
     *
     * @param description The description of the task that the user inputs
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a task that could be done or undone.
     *
     * @param description The description of the task that the user inputs
     * @param isDone Whether a task is done or undone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns X if a task is done, returns a blank space if it is not.
     *
     * @return A string which is either X or ' '
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string of a formatted task for saving
     *
     * @return String representation of formatted task
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
