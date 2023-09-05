package exceptions;

/**
 * Exception that encapsulations typical Kniaz excpetions we expect to run into at runtime
 * Typically, these involve I/O of some kind, such as invalid commands or invalid inputs
 * Most importantly, contains an additional user message that is meant to be seen by the user
 * And NOT for debugging/development purposes
 */
public class KniazRuntimeException extends RuntimeException {

    /**
     * The message that is meant to be seen by the user
     */
    private String userMessage = "";

    /**
     * Constructor for an exception, which constructs it with a non-user facing message, a user-facing message and
     * a cause
     * @param message the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param userMessage the message that IS meant to be seen as user feedback
     * @param cause what caused this, see java.Exceptions for more details
     */
    public KniazRuntimeException(String message, String userMessage, Throwable cause) {
        super(message, cause);
        this.userMessage = userMessage;

    }

    /**
     * Getter method for the user-facing message
     * @return the user-facing message of this exception
     */
    public String getUserMessage() {
        return userMessage;
    }
}
