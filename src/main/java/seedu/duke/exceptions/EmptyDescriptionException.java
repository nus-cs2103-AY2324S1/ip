package seedu.duke.exceptions;

/**
 * EmptyDescriptionException Exception
 */
public class EmptyDescriptionException extends Exception {
    private String message;

    /**
     * Exception for empty description in user input
     * @param message error message
     */
    public EmptyDescriptionException(String message) {
        super("EmptyDescriptionException: " + message + "\n");
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + "\n";
    }
}
