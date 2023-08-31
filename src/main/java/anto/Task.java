package anto;

/**
 * Class representing a Task.
 */
public class Task {
    /**
     * Description of task.
     */
    protected String description;

    /**
     * Boolean representing whether task is completed.
     */
    protected boolean isDone;

    /**
     * Creates a task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task in string form.
     *
     * @return Returns "X" if task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets task to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task to not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representing task.
     * @return String with task description.
     */
    public String toString() {
        return this.description;
    }
}
