package seedu.duke.exceptions;

/**
 * EmptyDescriptionException Exception
 */
public class EmptyDescriptionException extends Exception {
    private String message;

    /**
     * Constructor for empty description in user input
     *
     * @param message error message to parse into parent class
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
