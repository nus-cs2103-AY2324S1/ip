package duke.task;

/**
 * The abstract base class for tasks.
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructor for task.
     *
     * @param description A String describing the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        String msg = "[" + (isDone ? "X" : " ") + "]";
        msg = msg + " " + this.description;
        return msg;
    }
}
