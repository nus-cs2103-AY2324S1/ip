package avalon;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    /**
     * Enumerates the types of tasks: TODO, DEADLINE, EVENT.
     */
    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task is done (true) or not done (false).
     */
    protected boolean isDone;

    /**
     * Creates a new task with the given description and sets its initial completion status to false.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for the task, indicating whether it is done or not done.
     *
     * @return The status icon for the task ("[X] " if done, "[ ] " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its completion status to true.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting its completion status to false.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
