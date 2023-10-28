package duke.exceptions;

/**
 * Represents an exception that is thrown when an attempt to reschedule a task is invalid.
 */
public class InvalidRescheduleException extends Exception {

    /**
     * Constructs an InvalidRescheduleException with a specified error message.
     *
     * @param message The error message explaining why the rescheduling is invalid.
     */
    public InvalidRescheduleException(String message) {
        super(message);
    }
}
