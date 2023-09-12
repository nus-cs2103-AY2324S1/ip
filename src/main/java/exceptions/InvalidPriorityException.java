package exceptions;

import common.ExceptionMessage;

/**
 * Custom exception class for representing invalid priority.
 *
 * @author Ho Khee Wei
 */
public class InvalidPriorityException extends ThorndikeException {

    /**
     * Constructs an InvalidPriorityException with a message indicating that the
     * given priority is invalid.
     */
    public InvalidPriorityException() {
        super(ExceptionMessage.INVALID_PRIORITY);
    }
}
