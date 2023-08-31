package duke;

/**
 * Represents a task with a description and completion status. This is an abstract base class
 * that provides common functionality for different types of tasks.
 */
abstract public class Task {
    protected String description;
    protected Boolean isCompleted = false;
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param isCompleted True if the task is completed, false otherwise.
     */
    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the task in a format suitable for saving to a file.
     *
     * @return A formatted string representing the task's details for file storage.
     */
    abstract public String saveString();

    /**
     * Returns a string representation of the task including its completion status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        String box = this.isCompleted ? "[X]": "[ ]";
        return box + " " + this.description;
    }
}
