package duke.task;

/**
 * An abstract class which represents a task for Duke chatbot.
 *
 * @author Andrew Daniel Janong
 */
public abstract class Task {
    /** Task name or description */
    protected String description;

    /** Status or progress of a task (done or not done) */
    protected boolean isDone;

    /**
     * Creates a Task object.
     * A task is set to be not done when first constructed.
     *
     * @param description Description of the task.
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
     * Gets the icon that represents
     * the status of the task ([X] for done, [ ] for not done).
     *
     * @return The icon representing the progress of the task.
     */
    public String getStatusIcon() {
        if (this.isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Checks whether the task description contains
     * the input keyword.
     *
     * @param keyword Keyword to be checked.
     * @return True if contains keyword and False otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the representation of the task in data format.
     *
     * @return Data representation of the task.
     */
    public String getDataRepresentation() {
        if (this.isDone) {
            return "1|" + this.description;
        } else {
            return "0|" + this.description;
        }
    }

    /**
     * Returns the String representation of a task by its status and description.
     *
     * @return the String representing the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

}
