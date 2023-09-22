package martin.exceptions;

/**
 * Represents an exception when a marked task is marked again.
 * 
 * @param message Error message to be printed out.
 */
public class TaskAlreadyDoneException extends MartinException {
    public TaskAlreadyDoneException(String message) {
        super(message);
    }
}