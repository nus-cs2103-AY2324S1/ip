package duke.task;

/**
 * Represents a task with a description and status.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks the task as undone/done.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Provides a string representation of the task when saving.
     */
    public String toTxt() {
        return this.taskType + " | " + (this.isDone ? "1" : "0") + " | ";
    }

    /**
     * Provides a string representation of the task when listing.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
