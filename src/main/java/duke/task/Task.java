package duke.task;

/**
 * Represents a task with status indicating its completeness
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description The given description for the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Updates the task status to 'completed'
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Updates the task status to 'uncompleted'
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Event object in the data file format.
     *
     * @return String representation of the Deadline object in the data file format.
     */
    public abstract String toDataFormatString();
}
