package duke.exception;

/**
 * InvalidTaskException inherits from BobiException.
 * An InvalidTaskException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class InvalidTaskException extends BobiException {
    /**
     * Constructor of an InvalidTaskException object.
     */
    public InvalidTaskException() {
        super("Seems like Bobi cannot find the task you want!");
    }
}
