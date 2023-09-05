package seedu.duke.exceptions;

/**
 * EmptyDescriptionException Exception
 */
public class EmptyDescriptionException extends Exception {
    private String message;
    public EmptyDescriptionException(String message) {
        super("EmptyDescriptionException: " + message + "\n");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
