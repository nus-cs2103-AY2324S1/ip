package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when from time of event is missing.
 */
public class EmptyFromTimeException extends Exception {
    public EmptyFromTimeException(String message) {
        super(message);
    }
}
