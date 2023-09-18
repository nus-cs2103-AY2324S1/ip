package exceptions;

/**
 * The main chatbot exception for invalid commands and other general errors.
 *
 * @author Gallen Ong
 */
public class GBotException extends RuntimeException {
    /**
     * Initialises an invalid command exception.
     */
    public GBotException() {
        super("Sorry. My command range is simply limited, I don't understand the command.");
    }

    /**
     * Initalises a general chatbot exception.
     *
     * @param message The error message to be printed.
     */

    public GBotException(String message) {
        super(message);
    }
}
