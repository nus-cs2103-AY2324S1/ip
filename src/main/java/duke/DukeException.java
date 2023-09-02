package duke;

/**
 * Represents an exception that is specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the specified message.
     * @param message The message to be displayed when the exception is thrown.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the exception.
     * @return The method is returning a string representation of the exception.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
