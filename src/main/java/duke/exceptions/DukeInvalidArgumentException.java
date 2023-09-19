package duke.exceptions;

/**
 * Exception thrown when the user inputs an invalid argument to any command.
 */
public class DukeInvalidArgumentException extends DukeException {

    /**
     * Creates a new DukeInvalidArgumentException object.
     *
     * @param message The message of the exception.
     */
    public DukeInvalidArgumentException(String message) {
        super(message);
    }
}
