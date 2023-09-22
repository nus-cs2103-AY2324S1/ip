package martin.exceptions;

/**
 * Represents an exception when a task lacks a description.
 * 
 * @param message Error message to be printed out.
 */
public class EmptyTaskDescriptionException extends MartinException {
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }
}