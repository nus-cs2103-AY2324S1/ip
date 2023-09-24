package duke.exception;

/**
 * Represents an exception thrown when there's an invalid command input in Duke.
 */
public class InvalidCommandInputException extends DukeException {
    public InvalidCommandInputException(String message) {
        super(message);
    }
}
