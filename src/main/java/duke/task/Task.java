package duke.task;

/**
 * Represents a task in the chatbot application.
 */
public abstract class Task {
    protected String name;
    protected boolean isComplete;

    /**
     * Constructs a Task instance with the given name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    /**
     * Marks the task as completed.
     */
    public void taskDone() {
        this.isComplete = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void taskUndone() {
        this.isComplete = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return `true` if the task is completed, `false` otherwise.
     */
    public boolean getComplete() {
        return this.isComplete;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return A string representation of the task for saving.
     */
    public String toSave() {
        return "";
    }

    /**
     * Converts the task to a string.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return isComplete ? "[X] " + name : "[ ] " + name;
    }
}
