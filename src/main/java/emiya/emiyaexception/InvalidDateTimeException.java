package emiya.emiyaexception;

/**
 * An exception that is thrown when the user input contains an invalid date.
 */
public class InvalidDateTimeException extends EmiyaException {
    public InvalidDateTimeException() {
        super("Please enter a valid date/time!\n");
    }
}
