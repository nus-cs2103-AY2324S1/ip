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
        super("Sorry. I don't understand the command");
    }

<<<<<<< HEAD
    /**
     * Initalises a general chatbot exception.
     *
     * @param message The error message to be printed.
     */
=======
>>>>>>> branch-A-CodingStandard
    public GBotException(String message) {
        super(message);
    }
}
