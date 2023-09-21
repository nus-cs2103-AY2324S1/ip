package bob.exception;

/**
 * Exception to be thrown when user inputs a string command in
 * an invalid format.
 */
public class BobInvalidCommandException extends BobException {
    public BobInvalidCommandException() {
        super("Invalid command format chosen");
    }

    public BobInvalidCommandException(String message) {
        super(message);
    }
}
