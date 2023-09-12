package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Manages the date and time based on a user input.
 */
public class DateAndTime {

    /**
     * Parses a given string date. For dates only.
     * @param dateString The date in the specified format
     * @param format The format required.
     * @return The formatted date string
     */
    public String dayParse(String dateString, String format) {
        try {
            LocalDate date = LocalDate.parse(dateString);
            return date.format(DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            return dateString;
        }
    }

    /**
     * Checks if a given start and end date are valid.
     * @param start Start date
     * @param end End date
     * @return If the given date range is valid.
     */
    public boolean isValidDate(String start, String end) {
        try {
            return !LocalDate.parse(start).isAfter(LocalDate.parse(end));
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
