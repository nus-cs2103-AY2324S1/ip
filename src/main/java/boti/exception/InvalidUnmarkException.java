package boti.exception;

/**
 * The Exception for invalid mark command
 */
public class InvalidUnmarkException extends InvalidCommandException {
    /**
     * Instantiates of InvalidEventException
     */
    public InvalidUnmarkException() {
        super("☹ OOPS!!! I'm sorry, but the input unmark command is invalid :-(");
    }
}
