public class DukeException extends RuntimeException {
	private static final String prepend = "☹ OOPS!!!";

	static final String UNIDENTIFIED_COMMAND = "I'm sorry, but I don't know what that means :-(.";
	static final String OUT_OF_BOUNDS_TASK_LIST = "%d is out of bounds for the tasks list.";
	static final String ARGUMENT_MUST_BE_NUM = "The argument of %s must be a number.";

	public DukeException(String message) {
		super(String.format("%s %s", prepend, message));
	}
}
