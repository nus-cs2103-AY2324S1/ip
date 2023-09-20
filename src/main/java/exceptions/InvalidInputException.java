package exceptions;

/**
 * Exception class for invalid inputs
 */
public class InvalidInputException extends BocchiException {
    static final String INVALID_INPUT_ERROR_MSG = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Constructs a new InvalidInputException.
     */
    public InvalidInputException() {
        super(INVALID_INPUT_ERROR_MSG);
    }
}
