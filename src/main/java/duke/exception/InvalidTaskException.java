package duke.exception;

/**
 * The InvalidTaskException class represents an exception that is thrown when invalid taks
 * are input to a command in the Duke application.
 * It is a subclass of DukeException and provides a specific error message.
 */
public class InvalidTaskException extends DukeException {

    /**
     * Constructs a new InvalidTaskException with an error message.
     */
    public InvalidTaskException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
