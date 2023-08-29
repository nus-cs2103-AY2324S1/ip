package Duke.Exceptions;

/**
 * Custom exception class for Duke application.
 * This exception is used to handle errors specific to the Duke application.
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message associated with this exception.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}

