package taskstuff;

/**
 * An abstract class which can only be inherited from.
 * Serves as parent class of all tasks.
 */
public abstract class Task {

    /**
     * The name of the task.
     */
    protected String description;

    /**
     * Boolean variable indicating whether task is done.
     */
    protected boolean isDone;


    /**
     * Initialises name of task and isDone.
     *
     * @param description Name of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if task is complete.
     *
     * @return Returns X or empty space.
     */

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets isDone to true.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Sets isDone to false.
     */
    public void setAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a description of the task containing the status of the task.
     *
     * @return Returns a string with the name and the status of the task.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}

