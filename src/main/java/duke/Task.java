package duke;

/**
 * The Task class represents a generic task with a description and a completion status.
 * It serves as the base class for various types of tasks, such as To-Do, Deadlines, and Events.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;
    /**
     * A boolean indicating whether the task is marked as done (true) or not (false).
     */
    protected boolean isDone = false;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the status icon of the task, indicating whether it is done or not.
     *
     * @return A string containing "X" if the task is done, or " " (space) if it is not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String get() {
        return description; // mark done task with X
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void set() {
        this.isDone = true; // mark done task with X
    }

    /**
     * Marks the task as undone by setting its completion status to false.
     */
    public void unset() {
        this.isDone = false; // mark done task with X
    }

    /**
     * Returns an integer representation of the task's completion status.
     *
     * @return 1 if the task is done, or 0 if it is not done.
     */
    public int isDoneInt() {
        return this.isDone ? 1 : 0;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, or false if it is not done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task's status and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.get();
    }

    /**
     * Returns a string representation of the task suitable for saving to a file.
     *
     * @return A string containing the task's completion status (0 or 1) and description.
     */
    public String toSaveString() {
        String divider = " | ";
        return this.isDoneInt() + divider + this.description;
    }


    //...
}