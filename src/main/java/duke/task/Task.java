package duke.task;

/**
 * Represents the task added by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon, X for done, a space for not done.
     *
     * @return String represents the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Get the status in integer form, 1 for done, 0 for not done.
     *
     * @return Integer 1 if the task is done, 0 otherwise.
     */
    public int getStatusInteger() {
        return isDone ? 1 : 0;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the description of the task that is written to the data file.
     *
     * @return String representation of task stored in the data file.
     */
    public String writeFile() {
        return this.getStatusInteger() + " | " + this.description;
    }

    /**
     * Return the description of the task that is printed to the user.
     *
     * @return String representation of task printed to the user.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
