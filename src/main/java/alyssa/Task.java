package alyssa;

/**
 * Task represents a task created by a user. It can be either
 * done or undone.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor method for alyssa.Task.
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon corresponding to whether this task is done.
     * @return "X" if the task is done, and a whitespace otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     */
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
