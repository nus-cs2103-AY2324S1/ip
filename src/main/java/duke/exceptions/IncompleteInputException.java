package duke.exceptions;

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