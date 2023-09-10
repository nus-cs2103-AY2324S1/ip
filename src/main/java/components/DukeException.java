package components;

/**
 * Represents an exception specific to the Duke application.
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

    /**
     * Return error message.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return String.format("😭 OOPS!!! %s", super.getMessage());
    }
}
