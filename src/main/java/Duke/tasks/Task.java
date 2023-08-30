package Duke.tasks;
/**
 * Represents a general task with a description and completion status.
 */
public abstract class Task {
    protected boolean completed;
    private final String description;

    /**
     * Creates a new task with a given description.
     *
     * @param description The task's description.
     */
    public Task(String description) {
        this.description = description;
        this.completed = false;  // Tasks are uncompleted by default
    }

    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    /**
     * Checks if the task is completed.
     *
     * @return true if completed, otherwise false.
     */
    public boolean isCompleted() {
        return this.completed;
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
        this.completed = !this.completed;
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
