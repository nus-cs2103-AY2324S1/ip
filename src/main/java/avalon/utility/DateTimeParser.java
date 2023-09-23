package avalon.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing and formatting date-time objects.
 */
public class DateTimeParser {

    /**
     * Parses a string representation of a date-time and returns a LocalDateTime object.
     *
     * @param inputDateTime The input date-time string to be parsed.
     * @return The LocalDateTime object parsed from the input string.
     */
    public static LocalDateTime stringToDateTime(String inputDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, formatter);
            assert dateTime != null : "Parsing result should not be null";
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please follow the format: YYYY-MM-DD HHmm");
        }
        return null;
    }

    /**
     * Converts a LocalDateTime object to a formatted date-time string.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted date-time string.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return dateTime.format(formatter);
    }

    /**
     * Converts a LocalDateTime object to a human-readable formatted date-time string.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted human-readable date-time string.
     */
    public static String printDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDateTimeString = dateTime.format(formatter);
        assert formattedDateTimeString != null : "Formatted date-time string should not be null";
        return formattedDateTimeString;
    }
}
