package duke.exceptions;

/**
 * Thrown when the user inputs an invalid argument into Duke.
 */
public class DukeIllegalArgumentException extends DukeException {

    /**
     * Thrown when the user inputs an invalid argument.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeIllegalArgumentException(String message) {
        super(message);
    }
}
