package fluke.exceptions;

/**
 * This exception should be thrown whenever there is invalid input.
 */
public class InvalidInputException extends FlukeException {
    private static final String ERROR_MESSAGE = "I don't understand!";

    /**
     * Constructs an InvalidInputException.
     */
    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
