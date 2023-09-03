package duke.task;

/**
 * The abstract base class representing a task.
 *
 * @author Win Sheng
 * @since 3 September 2023
 */
public abstract class Task {
    public String description;
    public boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the type of the task.
     *
     * Subclasses must implement this method to specify the type of the task.
     *
     * @return The type of the task.
     */
    public abstract String type();

    /**
     * Converts the task to a string format for storing in a file.
     *
     * Subclasses must implement this method to specify the string representation of the task when saving it to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Gets a status icon representing the task's completion status.
     *
     * @return A status icon ("X" for done, " " for undone).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Updates the task's completion status and displays a message based on the expected status.
     *
     * @param expectedStatus The expected status of the task (true for done, false for undone).
     * @param doneMessage The message to display when the task is done.
     * @param undoneMessage The message to display when the task is undone.
     */
    public void updateTaskStatus(boolean expectedStatus, String doneMessage, String undoneMessage) {
        if (isDone == expectedStatus) {
            System.out.println(doneMessage);
        } else {
            isDone = expectedStatus;
            System.out.println(undoneMessage);
        }
    }

    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }
}
