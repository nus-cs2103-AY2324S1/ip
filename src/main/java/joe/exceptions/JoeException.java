package joe.exceptions;

/**
 * A custom exception class for handling errors in the Joe application.
 */
public class JoeException extends Exception {
    /**
     * Constructs a JoeException with a custom error message.
     *
     * @param error The error message describing the exception.
     */
    public JoeException(String error) {
        super(error);
    }
}
