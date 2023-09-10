package bert.exceptions;

/**
 * Represents an exception that is thrown when an empty task description is passed.
 */
public class BertEmptyTaskException extends BertException {
    /**
     * Constructs an empty task exception
     */
    public BertEmptyTaskException() {
        super("The description cannot be empty");
    }
}
