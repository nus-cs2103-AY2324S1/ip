package alpha;

/**
 * Class to handle exceptions related to entering tasks.
 */
public abstract class TaskException extends AlphaException {

    /**
     * Enumeration of task types.
     */
    public enum TaskType {
        DEADLINE,
        TODO,
        EVENT
    }

    private TaskType task;

    /**
     * Constructor for TaskException class.
     *
     * @param errorMessage The error message.
     * @param task         The type of task.
     */
    public TaskException(String errorMessage, TaskType task) {
        super(errorMessage);
        this.task = task;
    }

    /**
     * Return the type of the task.
     *
     * @return The task type.
     */
    public TaskType getTask() {
        return task;
    }
}
