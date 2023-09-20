package juke.commons.exceptions;

/**
 * Represents the error when the user tries to illegally modify the state of any
 * {@code JukeObject} or if the user tries to modify the state of any {@code JukeObject}
 * unsuccessfully.
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
