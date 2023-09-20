package bob.exceptions;

/**
 * Represents an exception when no task matches the command.
 */
public class BobInvalidTaskException extends BobException {

    /**
     * Constructor for this exception.
     */
    public BobInvalidTaskException() {
        super("Eyyyy, there is no such task!");
    }
}
