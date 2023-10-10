package tasks;


/**
 * The `Task` class represents a task that can be added to the task list.
 */
public class Task {
    private boolean isDone;
    private final String NAME;

    /**
     * Constructor for the Task class.
     *
     * @param name   The name of the task.
     * @param isDone Whether the task is marked as done or not.
     */
    Task(String name, boolean isDone) {
        this.NAME = name;
        this.isDone = isDone;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean checkDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task, including its name and status.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        String DONE = "[X] ";
        String NOTDONE = "[] ";
        return this.checkDone()
                ? DONE + this.NAME
                : NOTDONE + this.NAME;
    }

    /**
     * Marks the task as done.
     */
    public String markDone() {
        isDone = true;
        return "";
    }

    /**
     * Unmarks the task as done.
     */
    public String unmarkDone() {
        isDone = false;
        return "";
    }

    /**
     * Returns the name of the task
     *
     * @return name of task
     */
    public String getName() {
        return this.NAME;
    }
}
