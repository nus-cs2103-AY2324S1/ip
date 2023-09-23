package exceptions;

/**
 * Exception class for empty task descriptions.
 */
public class EmptyTaskException extends BocchiException {
    static final String EMPTY_TASK_ERROR_MSG = "OOPS!!! The description of a %s cannot be empty.";

    /**
     * Constructs a new EmptyTaskException with a specific task type.
     *
     * @param taskType The type of task (e.g., "deadline", "event", "todo").
     */
    public EmptyTaskException(String taskType) {
        super(String.format(EMPTY_TASK_ERROR_MSG, taskType));
    }
}
