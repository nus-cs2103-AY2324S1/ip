/**
 * Represents a singular general task.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public abstract class Task {
    /** A string description of the Task object. */
    protected String description;
    /** A boolean status of whether the Task is done. */
    protected boolean isDone;

    /**
     * A constructor to initialize the Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string description of the Task.
     *
     * @return The string description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the Task that will
     * be displayed to the user in the list.
     *
     * @return The string representation of the Task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Changes isDone boolean to true, marking task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes isDone boolean to true, marking task as done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of the status of the Task.
     *
     * @return The string representation of the status.
     */
    public String getStatusIcon() {
        return isDone
                ? "[X] "
                : "[ ] ";
    }

    /**
     * Returns the string representation of the task to be stored in a local file.
     *
     * @return The storage string representation of the task.
     */
    public abstract String toStorageString();
}
