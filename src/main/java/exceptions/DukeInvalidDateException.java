package exceptions;

/**
 * Represents an exception where the date of the task is invalid.
 *
 * @author Andrew Daniel Janong
 */
public class DukeInvalidDateException extends DukeException{
    public DukeInvalidDateException(String message) {
        super(message);
    }
}
