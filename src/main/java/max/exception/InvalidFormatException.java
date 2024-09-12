package max.exception;

/**
 * Signals invalid arguments were supplied by the user.
 */
public class InvalidFormatException extends MaxException {
    /**
     * Public constructor for InvalidFormatException.
     *
     * @param msg Error message
     */
    public InvalidFormatException(String msg) {
        super(msg);
    }
}
