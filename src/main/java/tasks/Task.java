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

    /**
     * Constructor, initializes task description.
     *
     * @param desc Task Description.
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /** Method signature for abstract method getTextFormattedString */
    protected abstract String getTextFormattedString();

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
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    protected void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representation of task.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.desc);
    }

}
