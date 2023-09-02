package duke;

/**
 * Represents a task in the Duke application.
 */
public class Task {

    /**
     * The type of the task (e.g., TODO, DEADLINE, EVENT).
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT,
        OTHERS
    }

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Indicates whether the task is marked as done (true) or not done (false).
     */
    protected boolean isDone;

    /**
     * The type of the task.
     */
    protected Type taskType;

    /**
     * Constructs a task with the specified description and task type.
     *
     * @param description The description of the task.
     * @param taskType    The type of the task.
     */
    public Task(String description, Type taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return "X" if the task is done, " " (a space) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
}
