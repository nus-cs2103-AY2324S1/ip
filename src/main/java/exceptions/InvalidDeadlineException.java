package exceptions;

/**
 * A DukeException for invalid deadline task command.
 */
public class InvalidDeadlineException extends DukeException {

    /**
     * Constructor, initializes the error message.
     */
    public InvalidDeadlineException() {
        super("Missing description/end for deadline task.\n"
                + "Format should be: deadline <task description> /by <yyyy-MM-dd HHmm>");
    }
}
