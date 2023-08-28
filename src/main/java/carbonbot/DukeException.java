package carbonbot;

/**
 * Thrown to indicate that an exception occurred that is specific to Duke.
 */
public class DukeException extends Exception {

	/**
	 * Constructs a DukeException with the specified detail message.
	 * @param errorMessage Error Message
	 */
	public DukeException(String errorMessage) {
		super(errorMessage);
	}
}
