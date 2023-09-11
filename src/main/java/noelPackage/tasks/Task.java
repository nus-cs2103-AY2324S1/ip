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

    private void markPrintHelper(String message) {
        String filler = "____________________________________________________________";
        System.out.println(filler);
        System.out.println(message);
        System.out.println(this);
        System.out.println(filler);
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
        if (!Noel.updateFromFile) {
            markPrintHelper(" Nice! I've marked this task as done:");
        }
    }

    /**
     * Marks the task as un-done
     */
    public void markAsUnDone() {
        this.isDone = false;
        if (!Noel.updateFromFile) {
            markPrintHelper(" OK, I've marked this task as not done yet:");
        }
    }

    public Boolean searchTaskName(String searchName) {
        return Objects.equals(this.taskName, searchName);
    }

}
