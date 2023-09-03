package bellcurvegod.task;

/**
 * A class encapsulating tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description.
     * @return Description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon depending on whether the task is done.
     * @return "X" if the task is done; " " otherwise.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
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
     * Returns a string representing the task for storage.
     * @return A string representing the task for storage.
     */
    public String getDataRepresentation() {
        String binaryStatus = this.isDone ? "1" : "0";
        return binaryStatus + "|" + this.description;
    }

    /**
     * Returns the string representation of a task.
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
