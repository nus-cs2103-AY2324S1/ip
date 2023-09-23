package pau.exception;

/**
 * Exception for an invalid index to get task.
 */
public class NoSuchTaskException extends Exception {
    /**
     * Constructs a NoSuchTaskException with an error message.
     */
    public NoSuchTaskException(String msg) {
        super(msg);
    }
}
