package corgi.tasks;

/**
 * Task status-related exception
 */
public class TaskStatusException extends TaskException {
    /**
     * Constructs a new TaskStatusException with the specified error message.
     *
     * @param msg The error message describing the specific task status error.
     */
    public TaskStatusException(String msg) {
        super(msg);
    }
}
