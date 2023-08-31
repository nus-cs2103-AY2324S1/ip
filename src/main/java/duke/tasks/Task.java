package duke.tasks;

/**
 * A Class to Represent a Task.
 */
public class Task {

    /** The description of the Task. */
    protected String description;

    /** The boolean to represent if the Task is Done. */
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the checkbox for the task.
     *
     * @return String [ ] if not done, [X] if done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Marks the Task as Done. */
    public void markTask() {
        this.isDone = true;
    }

    /** Marks the Task as Not Done. */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns the Formatted String of the Task to be saved into Storage.
     *
     * @return Formatted String representation of the task.
     */
    public String exportData() {
        return this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
