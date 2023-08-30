package zean.task;

/**
 * The class that represents a task.
 *
 * @author Zhong Han
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructor for the task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is marked as done, and " " otherwise.
     *
     * @return A string indicating the status of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     *
     * @return The string to be printed on the console.
     */
    public String markTaskDone() {
        this.isDone = true;
        return "\t  " + this;
    }

    /**
     * Marks the task as not done.
     *
     * @return The string to be printed on the console.
     */
    public String markTaskNotDone() {
        this.isDone = false;
        return "\t  " + this;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string comprising the status and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the string representing the task to be written in the disk.
     *
     * @return The string describing the task to be written in the disk.
     */
    public String toStringForFile() {
        if (this.isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
