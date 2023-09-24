package jarvis.exceptions;

/**
 * Represents an exception thrown when an invalid command is provided to Jarvis.
 */
public class InvalidCommandException extends JarvisException {
    /**
     * Returns exception message
     *
     * @param message exception message
     */
    public InvalidCommandException(String message) {
        super("Sorry Master, I'm not 100% sure what that means?\n"
                + "You may enter [help] to bring up the command menu.\n");
    }
}
