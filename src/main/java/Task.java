/**
 * Represent a task that is either done or not done.
 */
public class Task {
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

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
