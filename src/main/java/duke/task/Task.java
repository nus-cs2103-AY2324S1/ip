package duke.task;

/**
 * Represents a task with status indicating its completeness
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description The given description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        assert this.description != null : "Description of Task cannot be null";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task
     */
    @Override
    public String toString() {
        String status = "[" + (this.isDone ? "X" : " ") + "]";
        return status + " " + this.description;
    }

    /**
     * Updates the task status to 'completed'
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates the task status to 'uncompleted'
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}
