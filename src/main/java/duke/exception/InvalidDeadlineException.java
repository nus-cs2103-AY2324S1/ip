package duke.exception;

/**
 * This exception is thrown when there is an invalid deadline task given.
 * An invalid deadline task is one without a due date/time.
 */
public class InvalidDeadlineException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidDeadlineException() {
        super("☹ OOPS!!! You forgot to indicate the due date/time.");
    }
}
