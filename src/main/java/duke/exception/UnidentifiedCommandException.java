package duke.exception;

/**
 * Represents an exception when Duke cannot identify the command given. Thrown
 * when given a command
 * Duke is not aware of.
 */
public class UnidentifiedCommandException extends DukeException {
	/**
	 * Returns an UnidentifiedCommandException
	 *
	 * @return an UnidentifiedCommandException
	 */
	public UnidentifiedCommandException() {
		super("I'm sorry, but I don't know what that means :-(.");
	}
}
