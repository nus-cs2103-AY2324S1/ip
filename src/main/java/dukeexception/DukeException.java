package dukeexception;

/**
 * Represents any exceptions that are unique to Duke.
 */
public abstract class DukeException extends Exception {
    public DukeException() {
        super();
    }
    public DukeException(String s) {
        super(s);
    }
}
