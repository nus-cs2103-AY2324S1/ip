package mattbot.exception;

/**
 * Exception for when the task requested is not present.
 */
public class TaskNotFoundException extends Exception {

    /**
     * Creates a new instance of the exception.
     *
     * @param message The error message to be shown.
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}
