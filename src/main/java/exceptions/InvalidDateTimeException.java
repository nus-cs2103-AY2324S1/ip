package exceptions;

/**
 * Exception class for invalid date and times.
 */
public class InvalidDateTimeException extends BocchiException {
    static final String INVALID_DATE_TIME_ERROR_MSG =
            "Failed to parse the date-time string '%s'.\n"
                    + "Expected format: 'YYYY-MM-DD HH:MM:SS'.";

    /**
     * Constructs a new InvalidDateTimeException.
     */
    public InvalidDateTimeException(String dateTime) {
        super(String.format(INVALID_DATE_TIME_ERROR_MSG, dateTime));
    }
}
