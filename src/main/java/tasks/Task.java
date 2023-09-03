package tasks;

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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskAsString() {
        String message = String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        return message;
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
}