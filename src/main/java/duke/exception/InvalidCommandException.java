package duke.exception;

/**
 * This exception is thrown when there is an invalid command.
 * An invalid command is one that is not recognized by the program.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructor with an error message.
     */
    public InvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
