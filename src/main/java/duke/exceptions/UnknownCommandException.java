package duke.exceptions;

/**
 * Custom exception class for handling unknown commands.
 */
public class UnknownCommandException extends MyBotExceptions {

    /**
     * Constructs an UnknownCommandException instance with a default error message.
     */
    public UnknownCommandException() {
        super("☹ OOPS! I'm sorry, but I don't know what that means :(");
    }
}
