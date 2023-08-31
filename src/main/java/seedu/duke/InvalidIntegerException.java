package seedu.duke;

/**
 * Encapsulates the exception where the string that follows user's mark/delete operations is not
 * an integer.
 */
public class InvalidIntegerException extends DukeException {
    /**
     * Creates an InvalidIntegerException.
     */
    public InvalidIntegerException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! There is no such task.\n" +
                " Please input an integer.\n" +
                "____________________________________________________________");
    }
}
