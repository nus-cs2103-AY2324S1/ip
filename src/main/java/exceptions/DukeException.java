package exceptions;

/**
 * The DukeException class is a custom exception class that serves as the base class for all exceptions
 * specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructs a DukeException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public DukeException(String msg) {
        super(msg);
    }
}
