package DukeTasks;

/**
 * Encapsulates a DukeTasks.Task class. Contains a description and
 * a flag indicating whether the task instance is done.
 *
 * @author Tan Kerway
 */
public class Task {
    // tasks the bot has stored so far
    private final String description;
    // flag to indicate whether tha task has been completed
    private boolean isDone;

    /**
     * Constructor for the DukeTasks.Task class.
     *
     * @param description the description of the task to be instantiated
     */
    public Task(String description) {
        // create a new task instance
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for the DukeTasks.Task class.
     *
     * @param description the description of the task to be instantiated
     */
    public Task(String description, boolean isDone) {
        // create a new task instance
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Method that returns the status icon based on whether the task was completed.
     * If the task has been completed, "X" will be returned, else " " will be returned.
     *
     * @author Tan Kerway
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns whether the task has been done or not
     *
     * @author Tan Kerway
     * @returns true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of the task instance
     *
     * @author Tan Kerway
     * @return the description of the task instance
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone task flag to true.
     *
     * @author Tan Kerway
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone task flag to false.
     *
     * @author Tan Kerway
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Overrides the toString method.
     *
     * @author Tan Kerway
     * @return the string representation of a task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
