package boti.exception;

/**
 * The Exception for invalid mark command
 */
public class InvalidMarkException extends InvalidCommandException {
    /**
     * Instantiates InvalidMarkException
     */
    public InvalidMarkException() {
        super("☹ OOPS!!! I'm sorry, but the input mark command is invalid :-(");
    }
}
