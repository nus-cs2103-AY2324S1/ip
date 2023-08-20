/**
 * Task represents a task created by a user. It can be either
 * done or undone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor method for Task.
     * @param description A brief description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
