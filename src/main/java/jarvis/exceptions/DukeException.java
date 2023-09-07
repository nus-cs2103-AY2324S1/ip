package jarvis.exceptions;

/**
 * Represents the DukeException Class.
 *
 * @author Shishir
 */
public class DukeException extends Exception {

    /** Exception message. */
    private String message;

    /**
     * The constructor.
     * @param message The description of the error message.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the string representation of the exception.
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return this.message;
    }

}
