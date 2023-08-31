package monday.monday.dateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Class for parsing and formatting date and time using the specified pattern.
 */
public class DateFormatter {
    /**
     * Parses the given date and time string using the specified pattern.
     *
     * @param dateTime the date and time string to parse
     * @param pattern the pattern to use for parsing
     * @return the parsed LocalDateTime object
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static LocalDateTime parseTime(String dateTime, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. " +
                    "Please use the format: " + pattern);
        }
    }

    /**
     * Formats the given LocalDateTime object using the specified pattern.
     *
     * @param dateTime the LocalDateTime object to format
     * @param pattern the pattern to use for formatting
     * @return the formatted date and time string
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}
