package duke.utils;

import duke.exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;


/**
 * Provides utility methods for converting and manipulating date and time objects.
 */
public class DateTimeUtils {

    /**
     * Converts a LocalDateTime object to a formatted string representation.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The formatted string representation of the dateTime.
     */
    public static String localDateTimeToString(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

        String result = dateTime.format(dateFormatter);

        if (!(dateTime.getHour() == 0 && dateTime.getMinute() == 0)) {
            result += " " + dateTime.format(timeFormatter).toLowerCase();
        }

        return result;
    }

    /**
     * Converts a LocalDateTime object to a formatted string representation for db.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The formatted db string representation of the dateTime.
     */
    public static String localDateTimeToStringForDb(LocalDateTime dateTime) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        String result = dateTime.format(dateFormatter);

        if (!(dateTime.getHour() == 0 && dateTime.getMinute() == 0)) {
            result += " " + dateTime.format(timeFormatter).toLowerCase();
        }

        return result;
    }

    /**
     * Converts a date and time string to a LocalDateTime object. Supports multiple formats.
     *
     * @param dateTimeString The string representation of the date and time to be converted.
     * @return The corresponding LocalDateTime object.
     * @throws InvalidDateTimeException if the dateTimeString cannot be parsed into any supported format.
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeString) throws InvalidDateTimeException {
        List<String> patterns = Arrays.asList(
                "MMM d yyyy HHmm",
                "M/d/yyyy HHmm",
                "M/d/yyyy",
                "yyyy-MM-dd",
                "yyyy-MM-dd HHmm",
                "MMM d yyyy"
        );

        for (String pattern : patterns) {
            try {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                if (pattern.contains("H") || pattern.contains("h")) {
                    return LocalDateTime.parse(dateTimeString, formatter);
                }
                LocalDate date = LocalDate.parse(dateTimeString, formatter);
                return date.atStartOfDay();

            } catch (DateTimeParseException e) {
                // If parsing fails, continue to the next pattern
            }
        }
        System.out.println(dateTimeString);
        // If none of the patterns matched, throw an exception
        throw new InvalidDateTimeException();
    }
}
