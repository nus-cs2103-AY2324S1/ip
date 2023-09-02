package duke.exception;

/** This class handles exceptions for invalid commands from Duke bot*/
public class InvalidCommandException extends DukeException {

    /**
     * The constructor for InvalidCommandException
     * @param errorMsg The error message for the InvalidCommandException
     */
    public InvalidCommandException(String errorMsg) {
        super(errorMsg);
    }
}
