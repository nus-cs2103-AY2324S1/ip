package duke.data.exception;

/**
 * The DukeException class represents a custom exception specific to the ChatterChicken task manager application.
 * It is used to handle and convey application-specific errors and exceptional conditions.
 */
public class DukeException extends Exception {

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}