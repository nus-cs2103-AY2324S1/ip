package exceptions;

import common.ExceptionMessage;

/**
 * Custom exception class for representing invalid commands.
 *
 * @author Ho Khee Wei
 */
public class InvalidCommandException extends ThorndikeException {

    /**
     * Constructs an InvalidCommandException with a default error message for
     * unrecognized commands.
     */
    public InvalidCommandException() {
        super(ExceptionMessage.INVALID_COMMAND);
    }
}
