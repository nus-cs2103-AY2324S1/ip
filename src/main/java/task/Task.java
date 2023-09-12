package task;

/**
 * Represents a generic task.
 *
 * @author Ho Khee Wei
 */
public abstract class Task {
    private String description;
    private boolean isDone;
    private int priority;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = 0;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return "X" if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "  ");
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the priority of the task.
     *
     * @return The priority of the task.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     * 
     * @param priority The new priority.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format: "[Status][Priority] Description"
     */
    @Override
    public String toString() {
        return String.format("[%s][%d] %s", getStatusIcon(), priority, description);
    }
}
