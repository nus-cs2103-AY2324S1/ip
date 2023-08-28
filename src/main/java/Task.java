/**
 * Represents a single task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon
     * @return returns a string representation of the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }
}
