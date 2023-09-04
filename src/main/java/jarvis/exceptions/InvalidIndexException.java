package jarvis.exceptions;

/**
 * Represents an exception thrown when an invalid index is provided to Jarvis.
 */
public class InvalidIndexException extends JarvisException {

    public InvalidIndexException(String message) {
        super("    Master, this appears to be an invalid index.\n" + message);
    }
    
}
