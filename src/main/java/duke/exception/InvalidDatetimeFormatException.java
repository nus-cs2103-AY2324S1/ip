package duke.exception;

import duke.helper.DatetimeHelper;

/**
 * Represents an exception that the datetime input is not according to the
 * format specified
 */
public class InvalidDatetimeFormatException extends DukeException {
	/** 
	 * Returns an InvalidDatetimeFormatException
	 * @param argumentName the name of the argument
	 * @param command the name of the command
	 * @return an InvalidDatetimeFormatException
	 */
	public InvalidDatetimeFormatException(String argumentName, String command) {
		super(
				String.format(
						"The %s of %s must be in the format %s (i.e. %s)",
						argumentName, command, DatetimeHelper.inputFormat, DatetimeHelper.exampleDatetime));
	}
}
