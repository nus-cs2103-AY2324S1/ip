package duke.exceptions;

/**
 * Exception class that indicates the task is missing its description
 */
public class MissingTaskDescriptionException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}
