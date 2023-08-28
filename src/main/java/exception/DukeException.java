package exception;

/**
 * Exception thrown when an unrecognized util.command is provided to the Duke chatbot.
 */
public class DukeException extends Exception{
    /**
     * Constructs a exception.DukeException with a default error message.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
