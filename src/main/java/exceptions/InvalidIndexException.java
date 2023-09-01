package exceptions;

/**
 * Custom exception class for representing invalid task list indices.
 *
 * @author Ho Khee Wei
 */
public class InvalidIndexException extends ThorndikeException {

    /**
     * Constructs an InvalidIndexException with a message indicating that the given
     * index is invalid.
     */
    public InvalidIndexException() {
        super("The index given is invalid");
    }
}
