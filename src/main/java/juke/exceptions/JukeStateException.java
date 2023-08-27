package juke.exceptions;

/**
 * Represents the error when the user tries to modify the state of any
 * {@code JukeObject} unsuccessfully.
 */
public class JukeStateException extends JukeException {
    /**
     * Creates an instance of {@code JukeStateException}.
     *
     * @param error Error description
     */
    public JukeStateException(String error) {
        super(error);
    }
}
