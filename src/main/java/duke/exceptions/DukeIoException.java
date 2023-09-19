package duke.exceptions;

/**
 * The DukeIoException is used to represent an exception that occurs when there is an error
 * reading or writing to the local disk.
 */
public class DukeIoException extends DukeException {

    /**
     * Creates a new DukeIoException object.
     *
     * @param message The message of the exception.
     */
    public DukeIoException(String message) {
        super(message);
    }
}
