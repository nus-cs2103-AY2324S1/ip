package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * 
     * @return "X" if done, " " if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the status of the task.
     * 
     * @return true if done, false if not done.
     */
    public Boolean isDone() {
        return isDone;
    }

    /**
     * Sets task to done.
     * 
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets task to not done.
     * 
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the decription of the task.
     * 
     * @return Desription of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the string representation of the task.
     * 
     * @return String representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
