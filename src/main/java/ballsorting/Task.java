package ballsorting;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new instance of Task.
     * @param description Brief summary of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representation of the status of the Task.
     * @return String representation of Task status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the Task as done.
     * @return String representation of the task.
     */
    public String markDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Unmarks the Task as not done.
     * @return String representation of the task.
     */
    public String markNotDone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Returns a String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String toStorageString() {
        return "Task";
    }
}