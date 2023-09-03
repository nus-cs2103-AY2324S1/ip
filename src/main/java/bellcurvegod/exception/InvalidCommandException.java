package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when input is invalid.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
