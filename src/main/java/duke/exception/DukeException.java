package duke.exception;

/**
 * Custom exception class for representing exceptions specific to the Duke application.
 * This exception class is used to handle errors and exceptional situations in the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message explaining the reason for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
