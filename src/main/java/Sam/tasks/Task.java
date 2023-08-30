package sam.tasks;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Represents the task's status when printing.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Represents the task's encoded format in the hard disk.
     */
    public String toFileString() {
        return "| " + (this.isDone ? "1" : "0") + " | " + this.description;
    }
}
