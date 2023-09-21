package jarvis.exception;

/**
 * Represents an exception that is specific to Jarvis.
 */
public class JarvisException extends Exception {

    /**
     * Creates a new JarvisException with the specified message.
     *
     * @param message The message to be displayed.
     */
    public JarvisException(String message) {
        super(message);
    }
}