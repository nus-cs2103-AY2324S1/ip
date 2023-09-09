package duke.exception;

/**
 * FailedSearchException class inherits from DukeException class.
 * It encapsulates the error message to be displayed
 * when no task matches the keyword that the user has input.
 *
 * @author ruo-x
 */
public class FailedSearchException extends BobiException {
    /**
     * Constructor for a FailedSearchException object.
     */
    public FailedSearchException() {
        super("OOPS! Seems like there are no task that matches the given keyword. :/");
    }
}
