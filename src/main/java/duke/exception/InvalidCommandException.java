package duke.exception;
/**
 * The Exception class for invalid command
 */
public class InvalidCommandException extends Exception {
    // Constructor

    /**
     * The constructor of InvalidCommandException
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}