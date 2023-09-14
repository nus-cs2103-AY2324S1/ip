package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when description of task is missing.
 */
public class EmptyDescriptionException extends BellCurveGodException {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
