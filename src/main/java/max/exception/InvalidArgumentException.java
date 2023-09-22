package max.exception;

/**
 * Signals invalid arguments were supplied by the user.
 */
public class InvalidArgumentException extends MaxException {
    /**
     * Public constructor for InvalidArgumentException.
     *
     * @param msg Error message
     */
    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
