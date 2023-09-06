package dukepackage;

/**
 * Represents a Duke Exception. A DukeException object
 * will contain an error message represented by
 * a string.
 */
public class DukeException extends Exception {

    /**
     * Constructs the Duke Exception and sets the
     * error message for the exception.
     *
     * @param msg string printed when the exception is caught.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
