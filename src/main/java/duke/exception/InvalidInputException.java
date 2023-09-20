package duke.exception;

/**
 * Represents an exception that is due to invalid input from the user.
 */
public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
        super("Invalid Input: " + message);
    }
}
