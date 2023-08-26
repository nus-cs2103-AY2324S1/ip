package duke.task;



/**
 * Represents a general task. It can be extended by other specific task types.
 */
public class Task {
    public String description;
    public boolean isDone;
    public TaskType type;

    /**
     * Initializes a task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public TaskType getType() {
        return this.type;
    }
    /**
     * Initializes a task with the specified description, type, and completion status.
     *
     * @param description The description of the task.
     * @param type The type of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, TaskType type, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.type = type;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return "X" if the task is completed, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    // Commented code for getting DateTime.
    // public String getDateTime() {
    //     return dateTime.format(formatter);
    // }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task's status and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
