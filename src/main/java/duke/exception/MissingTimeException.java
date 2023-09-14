package duke.exception;

/**
 * MissingTimeException inherits from BobiException.
 * An MissingTimeException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class MissingTimeException extends BobiException {
    /**
     * Constructor of an MissingTimeException object.
     */
    public MissingTimeException() {
        super("Please input a date and time for this task.");
    }
}
