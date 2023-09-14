package duke.exceptions;

/**
 * Encaspulates an unknown command error.
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for an unknown command.
     * Defaults to an error message informing the user that the command is unknown.
     */
    public UnknownCommandException() {
        super("I'm sorry, I don't know what that means!");
    }
}
