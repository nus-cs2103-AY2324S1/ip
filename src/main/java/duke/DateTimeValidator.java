package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for validating dates using a specified format.
 */
public class DateTimeValidator {
    private DateTimeFormatter formatter;

    /**
     * Constructs a DateTimeValidator with the accepted date format.
     *
     * @param acceptedFormat The format string for the accepted date format.
     */
    public DateTimeValidator(String acceptedFormat) {
        this.formatter = DateTimeFormatter.ofPattern(acceptedFormat);
    }

    /**
     * Validates a date string against the accepted date format.
     *
     * @param date The date string to be validated.
     * @return True if the date string is valid; false otherwise.
     */
    boolean validateDate(String date) {
        try {
            formatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}