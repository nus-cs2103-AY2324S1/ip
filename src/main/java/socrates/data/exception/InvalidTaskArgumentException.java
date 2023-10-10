package socrates.data.exception;

/**
 * Represents an exception due to invalid task arguments.
 */
public class InvalidTaskArgumentException extends SocratesException {
    /**
     * Returns an instance of {@code SocratesException} with the given error message.
     *
     * @param message The  error message of the exception.
     */
    public InvalidTaskArgumentException(String message) {
        super(message);
    }
}
