package duke.exception;

/**
 * The DukeException class represents an exception that can be thrown by the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with a default error message.
     */
    public DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Constructs a DukeException with a custom error message.
     *
     * @param message The custom error message explaining the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
