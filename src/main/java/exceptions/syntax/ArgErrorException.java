package exceptions.syntax;

import exceptions.KniazRuntimeException;

/**
 * Exception thrown for when something is wrong with the arguments to a command.
 */
public abstract class ArgErrorException extends KniazRuntimeException {

    /**
     * Constructor for an exception, which constructs it with a non-user facing message, a user-facing message and
     * a cause
     *
     * @param message     the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param userMessage the message that IS meant to be seen as user feedback
     * @param cause       what caused this, see java.Exceptions for more details
     */
    public ArgErrorException(String message, String userMessage, Throwable cause) {
        super(message, userMessage, cause);

    }
}
