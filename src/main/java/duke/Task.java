package duke;

/**
 * A task.
 */
public class Task {
    // Description of a task, shown to users.
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     * @param description Description of new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to complete.
     */
    public void completeTask() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
