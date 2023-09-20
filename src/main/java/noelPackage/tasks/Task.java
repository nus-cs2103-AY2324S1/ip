package noelPackage.tasks;

import noelPackage.Noel;

import java.util.Objects;

/**
 * Represents a Task.
 */
public class Task {

    protected String taskName;
    protected boolean isDone;

    /**
     * Creates a new task with a given name.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Returns the status: done (X) or undone ( )
     * Used for System.out representation
     *
     * @return String representing status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status: done (1) or undone (0)
     * Used for file representation
     *
     * @return String representing status of task
     */
    public String getStatusNumber() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    /**
     * Returns the name of task
     *
     * @return String representation of the name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.taskName;
    }

    /**
     * Return a string representation of the task to be saved into a file
     *
     * @return String to save into a file
     */
    public String toFileString() {
        return "";
    }

    private String markPrintHelper(String message) {
        StringBuilder result = new StringBuilder();
        result.append(message).append(this);
        return result.toString();
    }

    /**
     * Marks the task as done
     */
    public String markAsDone() {
        this.isDone = true;
        if (!Noel.updateFromFile) {
            return markPrintHelper(" Nice! I've marked this task as done:");
        }
        return null;
    }

    /**
     * Marks the task as un-done
     */
    public String markAsUnDone() {
        this.isDone = false;
        if (!Noel.updateFromFile) {
            return markPrintHelper(" OK, I've marked this task as not done yet:");
        }
        return null;
    }

    public Boolean searchTaskName(String searchName) {
        return Objects.equals(this.taskName, searchName);
    }

}
