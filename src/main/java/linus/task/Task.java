package linus.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done linus.task with X
    }

    public abstract String getTaskTypeIcon();

    @Override
    public String toString() {
        assert this.description != null : "Task object should have non-null description.";

        return this.getTaskTypeIcon()
                + this.getStatusIcon() + " "
                + this.description;
    }
}
