package Kevin.Task;

/**
 * Encapsulates the tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method returns the status of this task.
     *
     * @return A string "X" if it is done otherwise an empty string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * This method sets the status of this task to be done.
     */
    public void setIsDone() {
        isDone = true;
    }

    /**
     * This method sets the status of this task to be not done.
     */
    public void setNotDone() {
        isDone = false;
    }

    public String toFileString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}