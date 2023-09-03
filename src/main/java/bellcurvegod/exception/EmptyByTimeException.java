package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when by time of deadline is missing.
 */
public class EmptyByTimeException extends Exception {
    public EmptyByTimeException(String message) {
        super(message);
    }
}
