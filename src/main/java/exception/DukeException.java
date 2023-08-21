package exception;

/**
 * exception.DukeException class is a custom exception class that extends Exception.
 */
public class DukeException extends Exception{

    /**
     * Constructor for exception.DukeException.
     * @param message The error message.
     */
    public DukeException(String message) {
        super(message);
    }
}
