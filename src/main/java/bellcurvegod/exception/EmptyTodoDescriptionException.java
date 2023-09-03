package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when description of todo is missing.
 */
public class EmptyTodoDescriptionException extends EmptyDescriptionException {
    public EmptyTodoDescriptionException(String message) {
        super(message);
    }
}
