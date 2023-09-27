package devybot.exceptions;

/**
 * Signals that a non-integer input has been provided when an integer task
 * number is expected in the DevyBot application.
 */
public class NonIntegerInputException extends DevyBotException {

    /**
     * Constructs a new NonIntegerInputException with a default error message.
     */
    public NonIntegerInputException() {
        super("☹ OOPS!!! Please provide a valid task number to mark.");
    }
}
