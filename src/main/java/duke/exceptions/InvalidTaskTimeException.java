package duke.exceptions;

/**
 * Exception class that indicates there is missing information on time inputs
 */
public class InvalidTaskTimeException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public InvalidTaskTimeException(String errorMessage) {
        super(errorMessage);
    }
}
