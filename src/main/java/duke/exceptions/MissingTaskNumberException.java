package duke.exceptions;

/**
 * Exception class that indicates no number was inputted
 */
public class MissingTaskNumberException extends DukeException {
    public MissingTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
