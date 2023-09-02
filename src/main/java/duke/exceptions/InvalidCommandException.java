package duke.exceptions;

/**
 * Exception class that indicates the input command doesn't exist
 */
public class InvalidCommandException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public InvalidCommandException(String errorMessage) {
        super(errorMessage);
    }
}
