package duke;

/**
 * Exception class for handling invalid numbers in Duke.
 */
public class InvalidNumberException extends DukeException {
    /**
     * Constructs a new InvalidNumberException with a default error message.
     */
    public InvalidNumberException() {
        super("OOPS!!! Invalid number.");
    }

    /**
     * Returns a string representation of this InvalidNumberException, including the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
