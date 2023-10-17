package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when description of deadline is missing.
 */
public class EmptyDeadlineDescriptionException extends EmptyDescriptionException {
    public EmptyDeadlineDescriptionException(String message) {
        super(message);
    }
}
