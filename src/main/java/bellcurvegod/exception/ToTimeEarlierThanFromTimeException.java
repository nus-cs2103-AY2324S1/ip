package bellcurvegod.exception;

/**
 * Encapsulates an exception thrown when end date is earlier than start date.
 */
public class ToTimeEarlierThanFromTimeException extends BellCurveGodException {
    public ToTimeEarlierThanFromTimeException(String message) {
        super(message);
    }
}
