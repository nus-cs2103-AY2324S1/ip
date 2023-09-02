package duke.exceptions;

/**
 * Parent exception for all custom exceptions
 */
public class DukeException extends Exception {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
