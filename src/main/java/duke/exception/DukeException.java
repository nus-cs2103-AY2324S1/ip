package duke.exception;

/**
 * Exception thrown when an unrecognized command is provided to the Duke chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with a default error message.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
