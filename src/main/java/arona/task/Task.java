package arona.task;

/**
 * Represents a task in the task list. A task has a description and can be marked as done or undone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description and sets it as undone by default.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new task with the given description and marked status.
     *
     * @param description The description of the task.
     * @param isMarked    The marked status (1 for marked, 0 for unmarked).
     */
    public Task(String description, int isMarked) {
        this.description = description;
        this.isDone = (isMarked == 1);
    }

    /**
     * Returns an icon representing the status of the task.
     *
     * @return A string containing "[X] " for marked tasks or "[ ] " for unmarked tasks.
     */
    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] ";
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }
}
