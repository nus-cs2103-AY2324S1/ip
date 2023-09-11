package duke.exception;

public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
        super("Invalid Input: " + message);
    }
}
