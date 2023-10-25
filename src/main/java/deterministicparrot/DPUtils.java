package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for date and time formatting and parsing in the Deterministic Parrot application.
 */
public class DPUtils {
    /**
     * Formats a LocalDateTime object into a string representation using the pattern "MMM d yyyy".
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String dPFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Formats a LocalDateTime object into a string representation using the pattern "yyyy-MM-dd".
     * This format is suitable for saving dates in files.
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return A formatted string representation of the LocalDateTime.
     */
    public static String saveFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Tries to parse a string representation of a date and time into a LocalDateTime object.
     * The supported format is "yyyy-MM-dd'T'HH:mm:ss".
     *
     * @param dateTime The string representation of the date and time.
     * @return A LocalDateTime object parsed from the input string.
     * @throws DateTimeParseException If the input string is not in the expected format.
     */
    public static LocalDateTime dPTryParseDateTime(String dateTime) throws DateTimeParseException {
        return LocalDateTime.parse(dateTime + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
