package duke.exception;

/**
 * Exception thrown when an unrecognized duke.util.duke.command is provided to the duke.Duke chatbot.
 */
public class DukeException extends Exception{
    /**
     * Constructs a duke.exception.DukeException with a default error message.
     */
    public DukeException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
