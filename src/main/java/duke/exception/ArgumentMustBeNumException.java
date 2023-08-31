package duke.exception;

public class ArgumentMustBeNumException extends DukeException {
	public ArgumentMustBeNumException(String argumentName) {
		super(String.format("The argument of %s must be a number.", argumentName));
	}
}
