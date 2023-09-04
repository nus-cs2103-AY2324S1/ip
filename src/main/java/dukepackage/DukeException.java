package dukepackage;

/**
 * Represents a Duke Exception. A DukeException object
 * will contain an error message represented by
 * a string.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }
}
