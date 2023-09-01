package duke.exceptions;

/**
 * Signals an invalid command provided by the user.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Public constructor for InvalidCommandException
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
