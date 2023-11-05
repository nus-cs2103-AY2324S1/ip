package Duke;

/**
 * Class to encapsulate the features of a task.
 */
public class Task {
    private String task;
    private boolean isDone;

    /**
     * Public constructor for Task.
     *
     * @param task The description of the task.
     */
    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Checks if the task description contains a specific string.
     *
     * @param val The string to check for in the task description.
     * @return true if the string is found, false otherwise.
     */
    public boolean contains(String val) {
        return task.contains(val);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string indicating whether the task is done and its description.
     */
    public String toString() {
        if (isDone)
            return "[X] " + task;
        return "[ ] " + task;
    }
}
