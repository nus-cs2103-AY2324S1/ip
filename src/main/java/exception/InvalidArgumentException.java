package exception;

/**
 * exception.InvalidArgumentException class is a custom exception class that extends DukeException.
 * It is thrown when the user inputs an invalid argument for a command.
 */
public class InvalidArgumentException extends DukeException{
    /**
     * Constructor for exception.InvalidArgumentException.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
