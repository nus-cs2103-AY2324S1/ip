package duke.exceptions;

/**
 * Exception class that indicates the task has no name
 */
public class MissingTaskNameException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public MissingTaskNameException(String errorMessage) {
        super(errorMessage);
    }
}
