package duke.exception;

/**
 * The duke.exception.InvalidInputException class represents a custom exception used in the duke.Duke class.
 * It extends the duke.exception.DukeException class.
 * It is thrown when the user input is invalid.
 */
public class InvalidInputException extends DukeException {
    /**
     * Constructor for duke.exception.InvalidInputException class.
     *
     * @param msg The error message of the exception.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }
}
