package duke.exception;

/**
 * EmptyTaskException inherits from BobiException.
 * An EmptyTaskException object encapsulates the error message
 * which will be shown when this exception is thrown.
 *
 * @author ruo-x
 */
public class EmptyTaskException extends BobiException {
    /**
     * Constructor of an EmptyTaskException object.
     */
    public EmptyTaskException() {
        super("Oh no! Bobi cannot add an empty task. :/");
    }
}
