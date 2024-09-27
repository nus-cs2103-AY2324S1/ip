package duke;

/**
 * Exception class for handling invalid date formats in Duke.
 */
public class InvalidDateException extends DukeException {
    /**
     * Constructs a new InvalidDateException with a default error message.
     */
    public InvalidDateException() {
        super("OOPS!!! Invalid date format.");
    }

    /**
     * Returns a string representation of this InvalidDateException, including the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
