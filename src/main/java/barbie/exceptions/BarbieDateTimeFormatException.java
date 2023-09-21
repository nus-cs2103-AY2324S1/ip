package barbie.exceptions;

/**
 * Throws an exception when the date and time format given is incorrect.
 */
public class BarbieDateTimeFormatException extends BarbieException {

    /**
     * Constructor for BarbieDateTimeFormatException.
     */
    public BarbieDateTimeFormatException() {
        super("Remember to put your deadlines or event timings in the format YYYY-MM-DD alright!");
    }
}
