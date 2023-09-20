package ipbot;

/**
 * Represents an exception in the command given by the user.
 */
public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message) {
        super(message);
    }
}
