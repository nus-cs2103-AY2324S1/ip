package duke.exceptions;

/**
 * Represents an exception when the user inputs an invalid input.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor for InvalidInputException.
     *
     * @param message Error message.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
