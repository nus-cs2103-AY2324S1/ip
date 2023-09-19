package duke;

/**
 * Represents a custom exception specific to the Duke chatbot application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Returns a formatted string representation of the exception.
     *
     * @return A formatted string containing the error message.
     */
    @Override
    public String toString() {
        return " ☹ OOPS!!! " + super.getMessage();
    }
}
