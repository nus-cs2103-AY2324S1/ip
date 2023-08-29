public class InvalidDatetimeFormatException extends DukeException {
	public InvalidDatetimeFormatException(String argumentName, String command) {
		super(String.format("The %s of a %s must be in the format %s (i.e. %s)", argumentName, command, DatetimeHelper.inputFormat, DatetimeHelper.exampleDatetime));
	}
}
