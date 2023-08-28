/**
 * Encapsulates a task. All tasks have a description and status (marked or unmarked).
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task object.
     *
     * @param description the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon depending on whether task has been marked as done or not.
     *
     * @return Status icon as a string.
     */
    private String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Mark task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a task with its status.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
