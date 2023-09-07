package duke.exceptions;

/**
 * Represents a Duke exception for handling program execution errors.
 */
public class DukeException extends Exception {
    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
