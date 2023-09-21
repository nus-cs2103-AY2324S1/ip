package dre.task;

/**
 * Represents a generic task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a default generic task.
     */
    public Task() {
        description = "default";
        isDone = false;
    }

    /**
     * Creates a new generic task.
     *
     * @param newTask The description of the task.
     */
    public Task(String newTask){
        description = newTask;
        isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done, no matter the current status of task.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Unmarks the task as not done, no matter the current status of task.
     */
    public void undo() {
        isDone = false;
    }

    /**
     * Generates the status icon that indicates whether the task is done.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Provides a formatted string for saving this task to a file.
     *
     * @return A formatted string suitable for file storage.
     */
    public String fileSaveFormat() {
        return getStatusIcon() + "|" + description;
    }

    /**
     * Converts the task to a string format for display.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task to a new String value.
     *
     * @param newDescription the new description to be refactored into the task.
     */
    public void editDescription(String newDescription) {
        this.description = newDescription;
    }
}