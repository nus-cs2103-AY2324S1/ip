public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of task.
     *
     * @param description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns status icon for task.
     * @return X if task is done and whitespace if not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task description.
     *
     * @return Task description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
