package alcazar.exceptions;

/**
 * Encapsulates the exception raised when an Invalid task is
 * passed as input
 */
public class InvalidTaskException extends AlcazarException {

    /**
     * Constructs a new Invalid Task exception
     * @param message The error message
     */
    public InvalidTaskException(String message) {
        super(message);
    }
}
