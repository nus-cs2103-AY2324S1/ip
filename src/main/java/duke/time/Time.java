package duke.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.exceptions.TimeParsingException;

/**
 * Provides utility methods for parsing and formatting date-time representations in the Duke application.
 * Supports specific date formats for displaying and storing purposes.
 *
 * @author YourName
 * @version 1.0
 * @since 2023-08-31
 */
public class Time {
    /**
     * Parses a string representation of a date into a {@link LocalDate} object.
     * The expected format is "yyyy-MM-dd".
     *
     * @param time The string representation of the date to be parsed.
     * @return A {@link LocalDate} object representing the parsed date.
     * @throws TimeParsingException If the provided string cannot be parsed into a valid date.
     */
    public static final LocalDate parseTime(String time) throws TimeParsingException {
        try {
            return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new TimeParsingException("Unable to parse time: " + time);
        }
    }

    /**
     * Formats a {@link LocalDate} object into a string representation.
     * The resulting format is "MMM dd yyyy" (e.g., "Jan 01 2023").
     *
     * @param time The {@link LocalDate} object to be formatted.
     * @return A string representation of the date.
     */
    public static final String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Formats a {@link LocalDate} object into a string representation suitable for storing.
     * The resulting format is "yyyy-MM-dd" (e.g., "2023-01-01").
     *
     * @param time The {@link LocalDate} object to be formatted.
     * @return A string representation of the date in a format suitable for storing.
     */
    public static final String formatTimeStoring(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
