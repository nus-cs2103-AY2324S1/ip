package didier.exception;

/**
 * Thrown to indicate that the date provided by the user is not in the correct format.
 */
public class DateFormatException extends DidierException {
    public DateFormatException() {
        super("The date must be in the format yyyy-mm-dd. ");
    }
}
