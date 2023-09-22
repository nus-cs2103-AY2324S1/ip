package boti.exception;

/**
 * The Exception for invalid list command
 */
public class InvalidListException extends InvalidCommandException {
    /**
     * Instantiates of InvalidListExeption
     */
    public InvalidListException() {
        super("☹ OOPS!!! I'm sorry, but the input list command is invalid :-(");
    }
}
