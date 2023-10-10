package alcazar.exceptions;

/**
 * Encapsulates an excpetion thrown when an invalid argument is passed
 */
public class InvalidArgumentException extends AlcazarException {

    /**
     * Constructs an InvalidArgumentException
     * @param message The error message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}