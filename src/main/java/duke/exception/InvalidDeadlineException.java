package duke.exception;
/**
 * The exception for invalid deadline command
 */
public class InvalidDeadlineException extends Exception {
    // Constructor

    /**
     * The constructor of InvalidDeadlineException
     */
    public InvalidDeadlineException() {
        super("☹ OOPS!!! I'm sorry, but the Deadline input is invalid :-(");
    }
}
