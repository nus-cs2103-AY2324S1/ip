package helpbuddy.task;

/**
 * An abstract class that defines task objects written by users.
 */
public abstract class Task {
    /** A string that indicates task name. */
    protected String description;
    /** A boolean that indicates whether task is completed by user. */
    protected boolean isDone;

    /**
     * A constructor of a task object.
     * @param description A string that indicates task name.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return the icon status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Updates the status of task.
     */
    public void updateDone() {
        this.isDone = !this.isDone;
    }

    /**
     * @return string representation of task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String stringifyTask();

}
