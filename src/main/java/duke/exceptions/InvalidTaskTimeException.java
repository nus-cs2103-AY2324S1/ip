package duke.exceptions;

/**
 * Exception class that indicates there is missing information on time inputs
 */
public class InvalidTaskTimeException extends DukeException {
    public InvalidTaskTimeException(String errorMessage) {
        super(errorMessage);
    }
}