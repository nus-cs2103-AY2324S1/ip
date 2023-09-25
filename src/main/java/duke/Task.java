package duke;

/**
 * Class to represent a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char taskType;

    /**
     * Initialises a task.
     *
     * @param description Short description of the task.
     * @param taskType Type of task.
     */
    public Task(String description, char taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status of the task whether done or not done.
     * @return Status of the task as [X] or [].
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task to be stored in a file.
     * @return File string representation of task.
     */
    public String toFileString() {
        return taskType + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + getStatusIcon() + " " + description;
    }
}
