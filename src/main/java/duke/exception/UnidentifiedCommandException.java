package duke.exception;

public class UnidentifiedCommandException extends DukeException {
	public UnidentifiedCommandException() {
		super("I'm sorry, but I don't know what that means :-(.");
	}
}
