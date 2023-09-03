package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when to time of event is missing.
 */
public class EmptyToTimeException extends Exception {
    public EmptyToTimeException(String message) {
        super(message);
    }
}
