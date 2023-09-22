package mattbot.exception;

/**
 * Exception for when the input is too short.
 */
public class ShortInputException extends Exception {

    /**
     * Creates a new instance of the exception.
     *
     * @param message The error message to be shown.
     */
    public ShortInputException(String message) {
        super(message);
    }
}
