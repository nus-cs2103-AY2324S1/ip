package boti.exception;

/**
 * The Exception for invalid bye command
 */
public class InvalidByeException extends InvalidCommandException {
    /**
     * Instantiates of InvalidEventException
     */
    public InvalidByeException() {
        super("☹ OOPS!!! I'm sorry, but the input bye command is invalid :-(");
    }
}
