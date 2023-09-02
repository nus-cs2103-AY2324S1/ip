package evo.exceptions;

/**
 * This exception is thrown to indicate that an input provided by the user is invalid which is not an integer.
 *
 * @author NgChunMan
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified error message.
     *
     * @param errorMessage A string that describes the specific error or reason for the exception.
     */
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }
}