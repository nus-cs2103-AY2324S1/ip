package exception;

import exception.AlphaException;

/**
 * Exception thrown when an input is not recognized.
 */
public class InvalidInputException extends AlphaException {
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}
