package duke.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for parsing and formatting date and time strings.
 */
public class DateParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Converts a date and time string to a LocalDateTime object.
     *
     * @param datetime The date and time string to be converted.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime convertStringToDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DATE_TIME_FORMATTER);
    }

    /**
     * Converts a LocalDateTime object to a formatted date and time string.
     *
     * @param datetime The LocalDateTime object to be converted.
     * @return A formatted date and time string.
     */
    public static String convertDateTimeToString(LocalDateTime datetime) {
        return datetime.format(DATE_TIME_FORMATTER);
    }

    /**
     * Checks if a date and time string is in the wrong format.
     *
     * @param datetime The date and time string to be checked.
     * @return false if the date and time string is in the correct format, true otherwise.
     */
    public static boolean isInvalidDateTime(String datetime) {
        try {
            LocalDateTime.parse(datetime, DATE_TIME_FORMATTER);
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}
