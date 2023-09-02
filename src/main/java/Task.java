/**
 * Represents a basic task that has a description.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor with description. isDone is set as false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing the completion status of the task.
     *
     * @return The status icon. ("X" representing completion)
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting isDone to true.
     */
    public void markAsDone() {
        if (!this.isDone) {
            this.isDone = true;
        }
    }

    /**
     * Changes the completion status back to not done. (set isDone as false)
     */
    public void unmark() {
        if (this.isDone) {
            this.isDone = false;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
