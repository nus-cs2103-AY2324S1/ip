package dukeexception;

/**
 * An extension of DukeException, that represents a bad command input.
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super();
    }
    public InvalidCommandException(String s) {
        super(s);
    }
}
