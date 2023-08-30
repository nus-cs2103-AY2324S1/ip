package exceptions.syntax;

/**
 * Thrown when there is some error in how the argument is formatted -- Like if it cannot be parsed into an int, etc.
 */
public class ArgFormatException extends ArgErrorException {
    /**
     * Constructor for this exception
     *
     * @param message     the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param userMessage the message that IS meant to be seen as user feedback
     * @param cause       what caused this, see java.Exceptions for more details,
     *                    typically an exception thrown by some other method
     */

    public ArgFormatException(String message, String userMessage, IllegalArgumentException cause) {
        super(message, userMessage, cause);

    }


}
