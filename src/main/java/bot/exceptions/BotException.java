package bot.exceptions;

/**
 * Supertype of all exceptions that occur specific to the bot.
 */
public class BotException extends Exception {
    /**
     * Default constructor.
     *
     * @param msg Message to be displayed when getMessage is called.
     */
    public BotException(String msg) {
        super(msg);
    }
}

