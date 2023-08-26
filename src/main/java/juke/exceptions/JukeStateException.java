package juke.exceptions;

/**
 * Represents the error when the user tries to modify the state of
 * any {@code JukeObject} unsuccessfully.
 */
public class JukeStateException extends JukeException {
    /**
     * Constructor to create a JukeStateException.
     *
     * @param err Error description
     */
    public JukeStateException(String err) {
        super(err);
    }
}
