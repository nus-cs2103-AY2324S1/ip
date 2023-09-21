package jarvis.exception;

/**
 * Represents an exception that occurs when the user provides an unrecognised command.
 */
public class JarvisUnrecognisedCommandException extends JarvisException {

    /**
     * Creates a new JarvisUnrecognisedCommandException.
     */
    public JarvisUnrecognisedCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
