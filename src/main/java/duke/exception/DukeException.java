package duke.exception;

/**
 * Represents the exceptions for Duke application.
 *
 * @author Joseph Oliver Lim
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with a specified message.
     *
     * @param message A message describing the error.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the DukeException.
     *
     * @return String representation of the DukeException.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
