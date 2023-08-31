package duke.exception;

/**
 * Represents an exception that a command cannot recover from. Message will be
 * printed by Printer
 */
public class DukeException extends RuntimeException {

	/**
	 * Returns a DukeException
	 *
	 * @param message The error message to print.
	 * @return a DukeException
	 */
	public DukeException(String message) {
		super(message);
	}
}
