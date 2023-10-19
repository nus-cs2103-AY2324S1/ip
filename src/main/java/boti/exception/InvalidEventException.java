package boti.exception;

/**
 * The Exception for invalid event command
 */
public class InvalidEventException extends InvalidCommandException {
    /**
     * Instantiates of InvalidEventException
     */
    public InvalidEventException() {
        super("☹ OOPS!!! I'm sorry, but the input Event is invalid :-(");
    }
}
