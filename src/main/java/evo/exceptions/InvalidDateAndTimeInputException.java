package evo.exceptions;

/**
 * This exception is thrown when the user types in an invalid date and time.
 *
 * @author NgChunMan
 */
public class InvalidDateAndTimeInputException extends Exception {

    /**
     * Constructs an InvalidDateAndTimeInputException object with the given error message.
     */
    public InvalidDateAndTimeInputException(String errorMsg) {
        super(errorMsg);
    }
}
