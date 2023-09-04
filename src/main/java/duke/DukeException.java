package duke;

/**
 * Represents an exception specific to the Duke application.
 * DukeException is used to handle custom exceptions within the Duke application.
 */
class DukeException extends Exception {

    /**
     * Creates a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with this DukeException.
     *
     * @return A formatted error message with the Duke application-specific prefix.
     */
    @Override
    public String getMessage() {
        return (":( OOPS!!! " + super.getMessage());
    }
}