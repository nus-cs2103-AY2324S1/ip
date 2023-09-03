package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when description of event is missing.
 */
public class EmptyEventDescriptionException extends EmptyDescriptionException {
    public EmptyEventDescriptionException(String message) {
        super(message);
    }
}
