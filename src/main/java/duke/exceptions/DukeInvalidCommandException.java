package duke.exceptions;

/**
 * Exception thrown when an invalid command is given.
 */
public class DukeInvalidCommandException extends DukeException {

    /**
     * Creates a new DukeInvalidCommandException object.
     *
     * @param message The message of the exception.
     */
    public DukeInvalidCommandException(String message) {
        super(message);
    }
}
