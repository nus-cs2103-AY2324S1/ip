public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     *
     * @param description the name/description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the completion status of this task in the form of an icon
     *
     * @return a string to indicate completion status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of this task, including its completion status & description
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks a task as complete
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }
}
