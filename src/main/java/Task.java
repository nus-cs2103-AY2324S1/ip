package main.java;
/**
 * An abstract class representing a task with a description and completion status.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task with the given description and default completion status (false).
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given description and specified completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task (true if done, false if not done).
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    /**
     * Returns a string representation of the task's completion status icon.
     *
     * @return The completion status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    /**
     * Returns a string representation of the task's description and completion status for saving.
     *
     * @return A formatted string containing completion status and description.
     */
    public String getSaveDescription() {
        return (this.isDone
                ? "| 1 | " + this.description
                : "| 0 | " + this.description);
    }
    /**
     * Marks the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }
    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        String tmp = "[" + this.getStatusIcon() + "]" + " " + this.description;
        return tmp;
    }

}
