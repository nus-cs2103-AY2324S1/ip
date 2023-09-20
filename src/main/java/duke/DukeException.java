package duke;

/**
 * Custom exception class for the Duke chatbot.
 * This exception is thrown when issues or errors occur in the Duke chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of the DukeException, which is the error message.
     *
     * @return The error message associated with the exception.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
