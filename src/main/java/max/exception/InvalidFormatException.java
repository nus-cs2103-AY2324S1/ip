package max.exception;

/**
 * Signals invalid arguments were supplied by the user.
 */
public class InvalidFormatException extends MaxException {
    /**
     * Public constructor for InvalidFormatException.
     *
     */
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
