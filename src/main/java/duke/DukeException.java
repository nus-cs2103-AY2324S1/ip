package duke;

/**
 * DukeException is a custom exception class used in the Duke application.
 * It is thrown to indicate exceptional situations or errors specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param msg The error message describing the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Gets the error message for this DukeException.
     *
     * @return A formatted error message with "OOPS!!! " prepended to the original message.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
