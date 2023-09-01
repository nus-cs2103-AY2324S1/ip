package exceptions;

/**
 * The DukeException class represents exceptions specific to the Duke application.
 * It provides a custom exception message to describe the nature of the exception.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param message The error message explaining the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
