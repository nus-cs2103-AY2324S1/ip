package dialogix.task;

/**
 * Represents a generic task in Dialogix.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Initializes a Task with a description.
     *
     * @param taskDescription The description of the task.
     */
    public Task(String taskDescription) {
        description = taskDescription;
        isDone = false;
    }

    /**
     * Gets the status icon representing whether the task is done or not.
     *
     * @return The status icon, "O" for done and "X" for not done.
     */
    private String getStatusIcon() {
        return (isDone ? "O" : "X"); // Return "O" or "X" symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the formatted output string for saving in a file.
     *
     * @return The formatted output string.
     */
    public String getOutputFormat() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Gets a string representation of the Task.
     *
     * @return The string representation of the Task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }
}
