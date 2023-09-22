package hachi.exceptions;

/**
 * Exception thrown when the date is in the wrong format, i.e. not yyyy-mm-dd
 */
public class DateFormatWrongException extends HachiException {
    /**
     * Constructor for the DateFormatWrongException class
     * @param date Wrongly formatted date given by user
     */
    public DateFormatWrongException(String date) {
        super(String.format("Your date is in the wrong format (%s). "
                + "Please enter the date in this format: yyyy-mm-dd", date));
    }
}
