package duke.data.exception;

/**
 * Repesents an exception due to an invalid command.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Returns an instance of {@code DukeException} with the given error message.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
