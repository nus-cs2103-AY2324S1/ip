package jarvis.exceptions;

/**
 * Represents an exception thrown when an invalid command is provided to Jarvis.
 */
public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String message) {
        super("Sorry Master, I'm not 100% sure what that means? Can you provide me with a more clear command please?");
    }
}
