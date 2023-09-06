package tong.exception;

/**
 * Signals that the input index to retrieve a tong.task.Task is invalid.
 */
public class TaskNotFoundException extends IndexOutOfBoundsException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
