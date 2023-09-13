package duke.exception;

/**
 * Represents an exception that is thrown when an invalid command is provided.
 */
public class InvalidCommandException extends DukeException {

    /**
     * Constructs a new InvalidCommandException with a default error message.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
