package alpha;

/**
 * Exception thrown when using a missing index to mark, delete, or unmark a task.
 */
public class MissingIndexException extends AlphaException {
    public MissingIndexException(String errorMessage) {
        super(errorMessage);
    }
}
