package bob.exception;

/**
 * Exception thrown when a command that requires an integer number
 * as task number is input wrongly.
 */
public class BobInvalidTaskNumberException extends BobException {
    public BobInvalidTaskNumberException() {
        super("Index chosen for task is invalid");
    }

    public BobInvalidTaskNumberException(String message) {
        super(message);
    }
}
