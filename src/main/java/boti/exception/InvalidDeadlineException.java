package boti.exception;

/**
 * The exception for invalid deadline command
 */
public class InvalidDeadlineException extends InvalidCommandException {
    /**
     * Instantiates of InvalidDeadlineException
     */
    public InvalidDeadlineException() {
        super("☹ OOPS!!! I'm sorry, but the Deadline input is invalid :-(");
    }
}
