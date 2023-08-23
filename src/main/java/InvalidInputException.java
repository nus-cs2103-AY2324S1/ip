/**
 * The InvalidInputException class represents a custom exception used in the Duke class.
 * It extends the DukeException class.
 * It is thrown when the user input is invalid.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor for InvalidInputException class.
     *
     * @param msg The error message of the exception.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
