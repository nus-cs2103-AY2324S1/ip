package duke.exceptions;

/**
 * Represents an exception when the user inputs an incomplete input.
 */
public class IncompleteInputException extends DukeException {
    /**
     * Constructor for IncompleteInputException.
     *
     * @param message Error message.
     */
    public IncompleteInputException(String message) {
        super(message);
    }
}
