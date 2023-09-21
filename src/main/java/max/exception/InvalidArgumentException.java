package max.exception;

/**
 * Signals invalid arguments were supplied by the user.
 */
public class InvalidArgumentException extends MaxException {
    /**
     * Public constructor for InvalidArgumentException.
     *
     */
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
