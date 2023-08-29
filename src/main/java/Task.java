import java.io.Serializable;

/**
 * Superclass that supports task methods
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task invoked by superclass
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * If tasks is done indicates "X" else shows a blank
     * @return returns a string of either X or blank
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void unmarkAsDone() { this.isDone = false; }

    /**
     * String representation of the task
     * @return details of the task
     */
    @Override
    public String toString() {
        String ret = "[" + getStatusIcon() + "] " + description;
        return ret;
    }
}