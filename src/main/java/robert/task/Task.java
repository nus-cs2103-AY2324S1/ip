package robert.task;

/**
 * The base class for tasks.
 *
 * @author Lee Zhan Peng
 */
public class Task {

    /** The description of the task */
    private final String description;

    /** The indication on whether it is marked */
    private boolean isDone;

    /**
     * Constructs a generic task.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Constructs a generic task.
     *
     * @param description the description of the task.
     * @param isDone whether the task is completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * A getter of the description.
     *
     * @return the description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * A getter of the status icon.
     *
     * @return the status icon of either "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the string representation of a generic task.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
