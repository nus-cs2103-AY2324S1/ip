package mattbot.exception;

/**
 * Exception for when the input is invalid.
 */
public class InvalidInputException extends Exception {

    /**
     * Creates a new instance of the exception.
     *
     * @param message The error message to be shown.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
