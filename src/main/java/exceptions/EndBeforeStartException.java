package exceptions;

import common.ExceptionMessage;

/**
 * Custom exception class for representing creating an event with the end date earlier than the start date.
 *
 * @author Ho Khee Wei
 */
public class EndBeforeStartException extends ThorndikeException {

    /**
     * Constructs an EndBeforeStartException exception with a message indicating that
     * the end date earlier than the start date.
     */
    public EndBeforeStartException() {
        super(ExceptionMessage.END_BEFORE_START);
    }
}
