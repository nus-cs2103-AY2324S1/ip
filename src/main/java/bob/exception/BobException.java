package bob.exception;

/**
 * A general exception that will be thrown when an executable command
 * encounters an error. Error message will be shown to standard output.
 */
public class BobException extends Exception {
    public BobException(String errorMessage) {
        super(errorMessage);
    }
}
