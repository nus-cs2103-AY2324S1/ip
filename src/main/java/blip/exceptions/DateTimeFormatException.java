package blip.exceptions;

/**
 * The DateTimeFormatException class is an exception class
 * that is thrown when a date time with wrong format is input.
 */
public class DateTimeFormatException extends Exception {
    /**
     * Creates an instance of DateTimeFormatException.
     *
     * @param message The error message
     */
    public DateTimeFormatException(String message) {
        super(message);
    }

}
