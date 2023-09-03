package duke.exception;

/**
 * This exception is thrown when there is an invalid event task given.
 * An invalid event task is one without a start and/or end date/time.
 */
public class InvalidEventException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidEventException() {
        super("☹ OOPS!!! You forgot to indicate the start and/or end date/time.");
    }
}
