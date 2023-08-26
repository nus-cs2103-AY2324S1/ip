package exceptions;

/**
 * Exception for when invalid arguments are provided to a command.
 */
public class InvalidArgumentException extends BotException{
    /**
     * Default constructor.
     */
    public InvalidArgumentException() {
        super("Sorry, but that command can't be done with the provided argument(s).");
    }
    /**
     * Creates an InvalidArgumentException with the provided string as the error
     * message.
     *
     * @param str Error message.
     */
    public InvalidArgumentException(String str) {
        super(str);
    }
}
