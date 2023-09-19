package hachi.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Exception thrown when the end date is before the start date.
 */
public class DateWrongOrderException extends HachiException {
    /**
     * Constructor for the DateWrongOrderException class
     * @param startDate start date to display
     * @param endDate end date to display
     */
    public DateWrongOrderException(LocalDate startDate, LocalDate endDate) {
        super(String.format("Your end date (%s) is before your start date (%s). "
                        + "Are you sure you entered them in the right order?",
                endDate.format(DateTimeFormatter.ISO_DATE),
                startDate.format(DateTimeFormatter.ISO_DATE)));
    }
}
