package seedu.duke.exceptions;

/**
 * EmptyDescriptionException Exception
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super("EmptyDescriptionException: " + message + "\n");
    }
}
