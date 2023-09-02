package duke.exceptions;

/**
 * Exception class that indicates the command is typed wrongly
 */
public class InvalidCommandSyntaxException extends DukeException {
	/**
	 * Creates a new instance of this exception
	 *
	 * @param errorMessage Error message to be displayed when exception is caught
	 */
    public InvalidCommandSyntaxException(String errorMessage) {
        super(errorMessage);
    }
}
