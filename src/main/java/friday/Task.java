package friday;

/**
 * Represents a task in the Friday application.
 */
public class Task extends Item {
    protected String name;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        super(name);
        this.isDone = false;
    }

    /**
     * Marks a status icon based on whether the task is done.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }

}