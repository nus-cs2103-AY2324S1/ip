package duke.exception;

/**
 * The DukeException class represents exceptions specific to the Duke application.
 * It is a subclass of RuntimeException and is used to handle custom application-related exceptions.
 */
public class DukeException extends RuntimeException{

    /**
     * Constructs a new DukeException with the specified error message.
     *
     * @param message The error message that describes the cause of the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
