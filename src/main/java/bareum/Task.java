package bareum;

/**
 * This class implements a task that has a description and completion status.
 */
public class Task {
    /**
     * Description of the task.
     */
    private String description;
    /**
     * Completion status of the task.
     */
    private boolean isDone;

    /**
     * Create a new task with the corresponding completion status and description.
     * @param isDone Completion status of the task.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    /**
     * Mark a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark a task as completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets an icon representation of the completion status of a task.
     * @return An icon representation of the completion status of a task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Create a string representation of the details of the task for saving into the hard disk.
     * @return String representation of the details of the task.
     */
    public String toSavedString() {
        return this.description;
    }
}
