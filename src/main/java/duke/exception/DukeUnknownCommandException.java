package duke.exception;

/**
 * Represents an exception that occurs when an unknown command is given.
 */
public class DukeUnknownCommandException extends DukeException {
    /**
     * Constructs a DukeUnknownCommandException.
     */
    public DukeUnknownCommandException() {
        super("I'm sorry, but I don't know what that means :-(\n");
    }
}
