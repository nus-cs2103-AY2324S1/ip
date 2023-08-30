package duke.exception;

/**
 * duke.exception.InvalidArgumentException class is a custom duke.exception class that extends DukeException.
 * It is thrown when the user inputs an invalid argument for a command.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Constructor for duke.exception.InvalidArgumentException.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
