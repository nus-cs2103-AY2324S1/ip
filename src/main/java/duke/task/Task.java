package duke.task;

/**
 * Represents a task to be added by the user.
 */

public class Task {
    protected String description;
    protected boolean isTaskCompleted;

    /**
     * Constructor
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isTaskCompleted = false;
    }

    public String getDescription() {
        return this.description;
    }
    public boolean getTaskStatus() {
        return this.isTaskCompleted;
    }

    public void markTaskCompleted() {
        this.isTaskCompleted = true;
    }
    public void markTaskUncompleted() {
        this.isTaskCompleted = false;
    }

    @Override
    public String toString() {
        if (this.isTaskCompleted) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
