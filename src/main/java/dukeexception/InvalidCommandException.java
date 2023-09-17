package dukeexception;

/**
 * An extension of DukeException, that represents a bad command input.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs a InvalidCommandException.
     */
    public InvalidCommandException() {
        super();
    }
    /**
     * Constructs a InvalidCommandException with a message.
     * @param s the associated message.
     */
    public InvalidCommandException(String s) {
        super(s);
    }
}
