package duke.exceptions;

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