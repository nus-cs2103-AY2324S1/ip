/**
 * Class that represents a task added by the user.
 */
public class Task {
    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean that represents whether the task is done.
     */
    protected boolean isDone;

    /**
     * Constructor used to create a task.
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of a Task.
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return this.description;
    }
}