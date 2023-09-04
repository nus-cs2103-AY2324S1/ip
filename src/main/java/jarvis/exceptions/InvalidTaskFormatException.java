package jarvis.exceptions;

/**
 * Represents an exception thrown when an invalid Task format is provided to Jarvis.
 */
public class InvalidTaskFormatException extends JarvisException{
    public InvalidTaskFormatException(String message) {
        super("Master, please provide me with the correct format.");
    }
}
