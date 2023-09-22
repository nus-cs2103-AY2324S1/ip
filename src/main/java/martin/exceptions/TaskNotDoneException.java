package martin.exceptions;


/**
 * Represents an exception when an unmarked task is unmarked again.
 * 
 * @param message Error message to be printed out.
 */
public class TaskNotDoneException extends MartinException {
    public TaskNotDoneException(String message) {
        super(message);
    }
}