package duke.task;

/**
 * A task.
 */
public class Task {
    private boolean isDone;
    private String description;

    /**
     * Creates a task instance.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Returns the current status of the task.
     *
     * @return A boolean value to indicate if the task is done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "/" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Desired string representation of the task.
     */
    public String convertToString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be inserted into a file.
     *
     * @return Desired string representation of the task.
     */
    public String convertToStringInFile() {
        if (isDone) {
            return " 1 / " + this.description;
        } else {
            return " 0 / " + this.description;
        }
    }
}
