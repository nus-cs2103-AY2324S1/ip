package duke.exception;
/**
 * Exception thrown when action entered is not a valid action.
 */
public class UnknownActionException extends DukeException {
    /**
     * Constructor for UnknownActionException.
     * @param errorMessage message to be displayed when error encountered
     */
    public UnknownActionException(String errorMessage) {
        super(errorMessage);
    }
}
