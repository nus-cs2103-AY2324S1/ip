package bert.exceptions;

/**
 * Represents an exception that is thrown when an invalid task command is passed.
 */
public class BertInvalidTaskException extends BertException {
    /**
     * Constructs an invalid task exception
     */
    public BertInvalidTaskException() {
        super("The task command is invalid");
    }
}
