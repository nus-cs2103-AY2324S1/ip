package Alex;

/**
 * An exception class that can be instantiated to represent exception related to Alex bot.
 */
public class AlexException extends Exception {
    /**
     * Constructor for AlexException.
     * @param message message to be wrapped in AlexException.
     */
    public AlexException(String message) {

        super(message);
    }

}
