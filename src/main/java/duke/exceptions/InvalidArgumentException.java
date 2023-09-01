package duke.exceptions;

/**
 * Signals an invalid argument provided by the user.
 */
public class InvalidArgumentException extends DukeException {
    /**
     * Public constructor for InvalidArgumentException
     */
    public InvalidArgumentException() {
        super("I'm sorry, but you have entered an invalid argument :-(");
    }
}
