package devybot.exceptions;

/**
 * Signals that an unknown or invalid command has been entered in the DevyBot
 * application.
 */
public class UnknownCommandException extends DevyBotException {

    /**
     * Constructs a new UnknownCommandException with a default error message.
     */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(. Please enter a valid command");
    }
}