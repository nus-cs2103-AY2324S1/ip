package tasks;

/**
 * Abstract class representing a task.
 * All specific tasks will be its child classes.
 */
public abstract class Task {

    /** The task description */
    protected String desc;

    /** Boolean indicating completion status */
    protected boolean isDone;

    /** Method signature for abstract method getTextFormattedString */
    protected abstract String getTextFormattedString();

    /**
     * Constructor, initializes task description.
     *
     * @param desc Task Description.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Returns string representation of completion status.
     *
     * @return String representation of completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     *
     * @return True.
     */
    protected boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Marks task as not done.
     *
     * @return True.
     */
    protected boolean unmark() {
        this.isDone = false;
        return true;
    }

    /**
     * Returns string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        String output = String.format("[%s] %s", getStatusIcon(), this.desc);
        return output;
    }

}
