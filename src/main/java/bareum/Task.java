package bareum;

/**
 * This class implements a task that has a description and completion status.
 */
public class Task {
    /**
     * Description of the task.
     */
    String description;
    /**
     * Completion status of the task.
     */
    boolean isDone;
    /**
     * Tag associated with the task.
     */
    String tag = "None";

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
     * Tag a task with the corresponding tag.
     */
    public void tag(String tag) {
        this.tag = tag;
    }

    /**
     * Gets an icon representation of the completion status of a task.
     * @return An icon representation of the completion status of a task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    public String getTag() {
        return this.tag;
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
