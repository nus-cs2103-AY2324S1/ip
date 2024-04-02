package dukeexception;

/**
 * Represents any exceptions that are unique to Duke.
 */
public abstract class DukeException extends Exception {
    /**
     * Constructs a DukeException.
     */
    public DukeException() {
        super();
    }
    /**
     * Constructs a DukeException with a message.
     * @param s the associated message.
     */
    public DukeException(String s) {
        super(s);
    }
}
