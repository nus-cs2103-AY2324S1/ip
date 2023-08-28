package carbonbot.task;

/**
 * This abstract class is the superclass of all classes representing a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task that has a completion status
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        // mark done task with X
        return (isDone ? "[X]" : "[ ]");
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
     * Serializes the task in a file friendly format
     *
     * @return String representation of the task
     */
    public abstract String serialize();


    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
