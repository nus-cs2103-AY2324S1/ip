public class Task {
    /**
     * the task description
     */
    protected String description;
    /**
     * variable to indicate if it is marked or not
     */
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param description the string of description that would like to be stored
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * return the status icon
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * mark the task done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * mark the task not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * override the toString method
     * @return a string
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

