package duke.exception;

/**
 * Custom exception class for Duke application.
 * It extends the Exception Class and provides a custom error message format.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with a specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the string representation of the exception.
     *
     * @return A string containing the formatted error message.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage();
    }
}
