package exceptions;

public abstract class KniazSyntaxException extends  KniazRuntimeException {

    /**
     * Constructor for an exception, which constructs it with a non-user facing message, a user-facing message and
     * a cause
     *
     * @param message     the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param userMessage the message that IS meant to be seen as user feedback
     * @param cause       what caused this, see java.Exceptions for more details
     */
    public KniazSyntaxException(String message, String userMessage, Throwable cause) {
        super(message, userMessage, cause);

    }
}
