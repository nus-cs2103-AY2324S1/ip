package bruno.exceptions;

/**
 * The BrunoDateTimeFormatException handles the case where the user has entered a date/time that is not in
 * "yyyy-MM-dd HH:mm" format.
 */
public class BrunoDateTimeFormatException extends BrunoException {

    public BrunoDateTimeFormatException() {
        super("Date and Time are not in correct format. They must be in YYYY-MM-DD HH:MM format.");
    }
}
