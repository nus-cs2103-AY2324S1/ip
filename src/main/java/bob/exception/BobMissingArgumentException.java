package bob.exception;

/**
 * Exception thrown when a command that requires an argument is
 * input without one.
 */
public class BobMissingArgumentException extends BobException {
    public BobMissingArgumentException() {
        super("You are missing an argument!");
    }

    public BobMissingArgumentException(String message) {
        super(message);
    }
}
