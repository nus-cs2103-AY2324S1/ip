package joe.tasks;

/**
 * An abstract class representing a task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private static final String DONE_SYMBOL = "X";
    private static final String NOT_DONE_SYMBOL = " ";

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " (whitespace) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_SYMBOL : NOT_DONE_SYMBOL);
    }

    /**
     * Gets the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the Task object to a formatted string.
     *
     * @return A string representation of the task, including its completion status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}
