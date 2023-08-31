package duke.task;

/**
 * Represents a task.
 *
 * @author Andrew Daniel Janong
 */
public abstract class Task {
    /**
     * The task name or description.
     */
    protected String description;

    /**
     * Marks whether the task is done or not.
     */
    protected boolean isDone;

    /**
     * A public constructor for the task.
     * A task is set to be not done when first constructed.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the icon that shows whether the task is done or not.
     * @return The icon representing the progress of the task ([X] for done, [ ] for not done)
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * The representation of the task to be used in the data.
     * @return Data representation of the task
     */
    public String toDataRepresentation() {
        if (this.isDone) {
            return "1|" + this.description;
        } else {
            return "0|" + this.description;
        }
    }

    /**
     * Shows the string representation of a task by its status and name.
     * @return the String representing the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
