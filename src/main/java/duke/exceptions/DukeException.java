package duke.exceptions;

/**
 * A generic exception class for Duke-related exceptions.
 */
public class DukeException extends RuntimeException {

    /**
     * Creates a new DukeException object.
     *
     * @param message The message of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
