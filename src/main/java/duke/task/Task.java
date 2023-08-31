package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with a description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an X icon based on whether a task is done.
     *
     * @return "X" if a task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether a task has been completed.
     *
     * @return True is task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks a task as done.
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     *
     */
    public void markNotDone() {
        this.isDone = false;
    }

    public String toFileString() {
        String type = "T"; // Default type for base Task
        return type + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
