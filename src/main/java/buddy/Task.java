package buddy;

/**
 * The task class represents a task in the tasklist.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * The constructor for a Task.
     * @param description The description of the task
     * @param isDone Status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markTaskAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markTaskAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the status of this task, whether it has been done or not.
     * @return "X" if the task is done, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets this task type.
     * @return A string representing the type of task
     */
    public String getTaskType() {
        return "";
    }

    /**
     * Converts this task to a format to be saved in the data file.
     * @return A string representing this task in the format to be saved in
     */
    public String toSaveFileFormat() {
        return String.format("%s | %d | %s",
                getTaskType(),
                isDone ? 1 : 0,
                this.description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "+ this.description;
    }
}
