package boti.exception;

/**
 * The Exception class for invalid command
 */
public class InvalidCommandException extends Exception {
    /**
     * Instantiates of InvalidCommandException
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Instantiates of InvalidCommandException
     */
    public InvalidCommandException(String message) {
        super(message);
    }
}
