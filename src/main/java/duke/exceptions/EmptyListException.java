package duke.exceptions;

/**
 * Exception class that indicates the list is empty
 */
public class EmptyListException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public EmptyListException(String errorMessage) {
        super(errorMessage);
    }
}
