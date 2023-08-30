package duke.exceptions;

/**
 * Exception class that indicates the list is empty
 */
public class EmptyListException extends DukeException {
    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}
