package duke;

/**
 * Represents an exception specific to the Duke application.
 * Extends the Exception class to handle Duke-related exceptions.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException object with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }

    /**
     * Retrieves the formatted error message for the DukeException.
     *
     * @return The formatted error message with a prefix ("OOPS!!!").
     */
    @Override
    public String getMessage() {
        return "OOPS!!! " + super.getMessage();
    }
}
