package duke.exceptions;

/**
 * Exception class that indicates the task number is out of bounds
 */
public class InvalidTaskNumberException extends DukeException {
    public InvalidTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
