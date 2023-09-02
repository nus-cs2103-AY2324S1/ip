package duke.exceptions;

/**
 * Exception class that indicates the task number is out of bounds
 */
public class InvalidTaskNumberException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public InvalidTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
