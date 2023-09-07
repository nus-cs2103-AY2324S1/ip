package duke.exceptions;

/**
 * Represents an exception when the user inputs a wrong mark.
 */
public class WrongMarkException extends DukeException {
    /**
     * Constructor for WrongMarkException.
     *
     * @param message Error message.
     */
    public WrongMarkException(String message) {
        super(message);
    }
}
