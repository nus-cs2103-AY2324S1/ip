package socrates.data.exception;

/**
 * Represents an exception due to an invalid task index.
 */
public class InvalidTaskIndexException extends SocratesException {

    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     * @param message The error message of the exception.
     */
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
