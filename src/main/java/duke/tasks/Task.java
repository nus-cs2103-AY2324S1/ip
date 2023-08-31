package duke.tasks;

/**
 * Encapsulates a task. All tasks have a description and status (marked or unmarked).
 */
public abstract class Task {
    private String description;
    private boolean isDone;

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

    /**
     * Returns the string representation of the task to be stored.
     *
     * @return String representing task to be stored.
     */
    public String tasktoString() {
        String statusNumber = isDone ? "1" : "0";
        return statusNumber + " | " + this.description;
    }

    /**
     * Checks if the task description contains the search target.
     *
     * @param target The search target input by user.
     * @return True if description contains target, false otherwise.
     */
    public boolean contains(String target) {
        return this.description.contains(target);
    }
}
