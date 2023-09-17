package seedu.dookie;

/**
 * Encapsulates the exceptions that are related to using the Dookie Chatbot.
 */
public class DookieException extends Exception {
    /**
     * Creates a new DookieException instance.
     *
     * @param message The message associated with the DookieException.
     */
    public DookieException(String message) {
        super(message);
    }
}
