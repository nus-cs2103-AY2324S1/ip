package duke.exceptions;

/**
 * Exception class that indicates no number was inputted
 */
public class MissingTaskNumberException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public MissingTaskNumberException(String errorMessage) {
        super(errorMessage);
    }
}
