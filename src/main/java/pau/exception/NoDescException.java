package pau.exception;

/**
 * Exception for an invalid description for tasks
 */
public class NoDescException extends Exception {
    /**
     * Constructs a NoDescException with an error message.
     */
    public NoDescException(String msg) {
        super(msg);
    }
}
