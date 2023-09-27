package chatterbot.exceptions;

/**
 * This class inherits from ChatterBotException.
 * An instance is created when an invalid deadline is entered by the user.
 */
public class InvalidDeadlineException extends ChatterBotException {
    public InvalidDeadlineException() {
        super("Invalid deadline! Enter in this format instead: YYYY-MM-dd.");
    }
}
