package evo.exceptions;

/**
 * This exception is thrown to indicate that an input provided by the user is invalid.
 *
 * @author NgChunMan
 */
public class DoAfterException extends Exception {
    /**
     * Constructs an DoAfterException object with the given error message.
     *
     * @param message The error message that describes the exception.
     */
    public DoAfterException(String message) {
        super(message);
    }
}
