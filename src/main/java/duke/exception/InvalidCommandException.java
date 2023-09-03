package duke.exception;

/**
 * InvalidCommandException inherits from BobiException.
 * An InvalidCommandException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class InvalidCommandException extends BobiException {
    /**
     * Constructor of an InvalidCommandException object.
     */
    public InvalidCommandException() {
        super("Oh no! Bobi does not know what that means... :/");
    }
}
