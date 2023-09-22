package duke.exception;
/**
 * Exception thrown when action entered is not a valid action.
 */
public class UnknownActionException extends DukeException {
    /**
     * Constructor for UnknownActionException.
     */
    public UnknownActionException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
