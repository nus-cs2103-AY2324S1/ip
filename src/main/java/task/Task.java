package task;

/**
 * Represents a task
 */
public class Task {

    /**
     * Description of the task
     */
    protected String description;

    /**
     * True if the task is done, false otherwise
     */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon depending on whether the task is done
     *
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getType() {
        return " ";
    }

    public String getDetails() {
        return this.description;
    }

    /**
     * Returns a string representation of the task
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
