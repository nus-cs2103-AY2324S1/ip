package bert.exceptions;

/**
 * Represents an exception that is thrown when an invalid command is passed.
 */
public class BertInvalidCommandException extends BertException {
    /**
     * Constructs an invalid command exception.
     */
    public BertInvalidCommandException() {
        super("The task command is invalid");
    }
}
