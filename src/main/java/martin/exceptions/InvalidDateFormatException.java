package martin.exceptions;

/**
 * Represents an exception when a date is formatted wrongly. 
 * 
 * @param message Error message to be printed out.
 */
public class InvalidDateFormatException extends MartinException {
    public InvalidDateFormatException(String message) {
        super(message);
    }
}