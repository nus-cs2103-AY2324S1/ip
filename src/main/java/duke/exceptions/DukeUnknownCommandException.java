package duke.exceptions;

/**
 * Thrown when the user inputs an unknown command into Duke.
 */
public class DukeUnknownCommandException extends DukeException {

    /**
     * Thrown when the user inputs an unknown command.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeUnknownCommandException(String message) {
        super(message);
    }
}
