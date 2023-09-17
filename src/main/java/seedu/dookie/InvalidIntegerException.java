package seedu.dookie;

/**
 * Encapsulates the exception where the string that follows user's mark/delete operations is not
 * an integer.
 */
public class InvalidIntegerException extends DookieException {
    /**
     * Creates an InvalidIntegerException.
     */
    public InvalidIntegerException() {
        super("\u2639 OOPS!!! There is no such task.\n" +
                " Please input an integer.");
    }
}
