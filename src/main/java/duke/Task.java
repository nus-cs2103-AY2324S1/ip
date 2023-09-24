package duke;

/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns either a blank or "X" depending on the isDone of a Task.
     *
     * @return A string "X" or an empty string " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the boolean isDone of a Task to true.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the boolean isDone of a Task to false.
     *
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task with its status and description.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be saved into a file.
     *
     * @return A string representing the task in a file.
     */
    public String getDescription() {
        return " |" + this.getStatusIcon() + "| " + this.description;
    }

    /**
     * Sets the description of a Task to the input description.
     *
     * @param newDescription The new description to be set to.
     */
    public void editDescription(String newDescription) {
        this.description = newDescription;
    }
}
