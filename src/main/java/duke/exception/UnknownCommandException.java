package duke.exception;

/**
 * Represents an exception that occurs when user inputs an unknown or unrecognised command.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructs an UnknownCommandException with an error message indicating an unrecognised command.
     */
    public UnknownCommandException() {
        super(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
