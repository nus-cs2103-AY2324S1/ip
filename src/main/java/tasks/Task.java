package tasks;

/**
 * The Task class represents a basic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task instance with the specified
     * description and sets the completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task instance with the specified description and completion status.
     *
     * @param des  The description of the task.
     * @param mark The completion status of the task.
     */
    public Task(String des, boolean mark) {
        this.description = des;
        this.isDone = mark;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return The completion status icon ("[X]" for completed, "[ ]" for not completed).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void toMark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void toUnmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param key The keyword to search for in the task's description.
     * @return True if the description contains the keyword, otherwise false.
     */
    public boolean containKey(String key) {
        return this.description.contains(key);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }
}
