package exceptions;

/**
 * A DukeException for invalid event task command.
 */
public class InvalidEventException extends DukeException {

    /**
     * Constructor, initializes the error message.
     */
    public InvalidEventException() {
        super("Missing description/start/end for event task.\n"
                + "Format should be: event <task description> "
                + "/from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
    }
}
