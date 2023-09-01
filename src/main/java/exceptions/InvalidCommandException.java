package exceptions;

/**
 * Custom exception class for representing invalid commands.
 *
 * @author Ho Khee Wei
 */
public class InvalidCommandException extends ThorndikeException {

    /**
     * Constructs an InvalidCommandException with a default error message for
     * unrecognized commands.
     */
    public InvalidCommandException() {
        super("I'm sorry, but I don't know what that means :-(");
    }
}
