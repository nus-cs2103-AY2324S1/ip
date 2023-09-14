package duke.exception;

/**
 * EmptyCommandException inherits from BobiException.
 * An EmptyCommandException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class EmptyCommandException extends BobiException {
    /**
     * Constructor of an EmptyCommandException object.
     */
    public EmptyCommandException() {
        super("Oh no! Bobi cannot receive empty commands. :/");
    }
}
