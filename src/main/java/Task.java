public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status icon of the task
     *
     * @return X if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        this.isDone = true;
        return;
    }

    /**
     * Marks task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
        return;
    }

    /**
     * Returns the data representation of the task.
     */
    public abstract String toData();

    /**
     * Returns string representation of task
     *
     * @return String representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    
}