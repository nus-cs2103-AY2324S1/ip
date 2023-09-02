package fluke.exceptions;

/**
 * This exception should be thrown when an operation tries to access a task that does not exist.
 */
public class TaskDoesNotExistException extends FlukeException {
    private static final String ERROR_MESSAGE = "I'm pretty sure that task doesn't exist...";

    /**
     * Constructs a TaskDoesNotExistException.
     */
    public TaskDoesNotExistException() {
        super(ERROR_MESSAGE);
    }
}
