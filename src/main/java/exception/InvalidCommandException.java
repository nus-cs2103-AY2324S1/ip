package exception;

/**
 * Custom Exception class that extends exception.DukeException. It is thrown when the user input command to
 * the chatbot is invalid.
 *
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs new exception.InvalidCommandException with specified error messages.
     *
     * @param message The message of the invalid command given by the user.
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
