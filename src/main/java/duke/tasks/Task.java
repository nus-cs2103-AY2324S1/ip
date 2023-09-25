package duke.tasks;

/**
 * A <code>Task</code> object tracks a singular task that the user
 * has noted with a description of said task, and whether or not it is done.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The class constructor.
     *
     * @param description Description of the <code>Task</code> object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the isDone value, for display to user.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the isDone value, for writing to file.
     */
    public String getStatusBinary() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks the task as finished.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as unfinished.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string form of the <code>Task</code> object, for writing to file.
     */
    public String storedString() {
        return this.getStatusBinary() + " | " + this.description;
    }

    /**
     * Returns the string form of the <code>Task</code> object, for display to user.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}

