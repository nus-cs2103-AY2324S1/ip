package duke.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for parsing and formatting date and time strings.
 */
public class DateParser {

    /**
     * Converts a date and time string to a LocalDateTime object.
     *
     * @param datetime The date and time string to be converted.
     * @return A LocalDateTime object representing the parsed date and time.
     */
    public static LocalDateTime convertStringToDateTime(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date format. Please enter in the format 01-01-2001 01:01");
        }
        return null;
    }

    /**
     * Converts a LocalDateTime object to a formatted date and time string.
     *
     * @param datetime The LocalDateTime object to be converted.
     * @return A formatted date and time string.
     */
    public static String convertDateTimeToString(LocalDateTime datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return datetime.format(formatter);
    }
}
