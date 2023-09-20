package exceptions;

/**
 * A DukeException for an unknown command by the user.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructor, initializes the error message.
     */
    public UnknownCommandException() {
        super("OOPS!!! Your input is either an unknown command, "
                + "or contains the vertical bar character | .");
    }
}
