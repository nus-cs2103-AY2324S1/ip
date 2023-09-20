package tasks;

/**
 * The base class for different types of tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A public constructor to initialize a task
     *
     * @param description  a description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A public constructor to initialize a task
     *
     * @param description  a description of the task
     * @param isDone task completion status
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the completion status of task.
     *
     * @return A String object representing the completion status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the String representation of task.
     *
     * @return A String representation of task.
     */
    public String getTaskAsString() {
        String message = String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        return message;
    }

    /**
     * Marks tasks as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks tasks as undone
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the String description of task.
     *
     * @return A String description of task.
     */
    public String getDescription() {
        return this.description;
    }
}
