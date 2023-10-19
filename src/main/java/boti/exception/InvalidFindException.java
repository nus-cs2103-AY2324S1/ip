package boti.exception;

/**
 * The Exception for invalid find command
 */
public class InvalidFindException extends InvalidCommandException {
    /**
     * Instantiates of InvalidFindException
     */
    public InvalidFindException() {
        super("☹ OOPS!!! I'm sorry, but the input find command is invalid :-(");
    }
}
