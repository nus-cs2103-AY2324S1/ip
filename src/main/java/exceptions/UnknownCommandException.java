package exceptions;

/**
 * A DukeException for an unknown command by the user.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructor, initializes the error message.
     */
    public UnknownCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
