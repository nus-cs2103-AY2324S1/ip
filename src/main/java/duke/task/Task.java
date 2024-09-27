package duke.task;

/**
 * The Task class.
 */
public class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructor for the duke.task.Task class.
     *
     * @param name The name of the task.
     * @param isDone Whether the task is marked done or not.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representing the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String userDisplayString() {
        return null;
    }

    /**
     * Returns the name of the task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
