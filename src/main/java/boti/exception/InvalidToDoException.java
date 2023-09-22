package boti.exception;

/**
 * The Exception for invalid todo command
 */
public class InvalidToDoException extends InvalidCommandException {
    /**
     * Instantiates of InvalidToDoException
     */
    public InvalidToDoException() {
        super("☹ OOPS!!! I'm sorry, but the input ToDo is invalid :-(");
    }
}
