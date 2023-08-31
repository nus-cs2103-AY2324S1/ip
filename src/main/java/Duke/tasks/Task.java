package Duke.tasks;
/**
 * Represents a general task with a description and completion status.
 */
public abstract class Task {
    protected boolean isCompleted;
    private final String description;

    /**
     * Creates a new task with a given description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;  // Tasks are unisCompleted by default
    }


    /**
     * Checks if the task is isCompleted.
     *
     * @return true if isCompleted, otherwise false.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Retrieves the task's description.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Toggles the completion status of the task.
     */
    public void toggleCompleted() {
        this.isCompleted = !this.isCompleted;
    }

    /**
     * Provides a type identifier for the task.
     * Since this is a general task, it returns an empty string.
     *
     * @return Type of the task.
     */
    public String getType() {
        return "";
    }

    /**
     * Offers a formatted string showing the task's type, completion status, and description.
     *
     * @return Formatted string of the task.
     */
    @Override
    public String toString() {
        String completionStatus = isCompleted() ? "[X] " : "[ ] ";
        String taskType = "[" + getType() + "]";
        return taskType + completionStatus + description;
    }
}
