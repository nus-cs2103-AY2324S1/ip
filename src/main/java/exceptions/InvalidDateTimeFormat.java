package exceptions;

/**
 * Custom exception class for representing invalid date and time formats.
 *
 * @author Ho Khee Wei
 */
public class InvalidDateTimeFormat extends ThorndikeException {

    /**
     * Constructs an InvalidDateTimeFormat exception with a message indicating that
     * the given format is wrong.
     */
    public InvalidDateTimeFormat() {
        super("The given format is wrong!");
    }
}
