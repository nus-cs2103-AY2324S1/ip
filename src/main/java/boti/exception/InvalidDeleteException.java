package boti.exception;

/**
 * The Exception for invalid delete command
 */
public class InvalidDeleteException extends InvalidCommandException {
    /**
     * Instantiates of InvalidDeleteException
     */
    public InvalidDeleteException() {
        super("☹ OOPS!!! I'm sorry, but the input delete command is invalid :-(");
    }
}
