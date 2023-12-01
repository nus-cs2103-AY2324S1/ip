package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeFormatter is used to format LocalDateTime objects and date strings.
 */
public class DateTimeFormat {
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Convert the date and time string to a LocalDateTime object.
     *
     * @param dateTimeString The date and time string in "yyyy-MM-dd HH:mm" format.
     * @return The LocalDateTime object corresponding to the date and time of the string given.
     */
    public static LocalDateTime toLocalDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    /**
     * Convert the LocalDateTime object to a date and time string.
     *
     * @param ldt The LocalDateTime object to be converted.
     * @return The string represntation of the date and time in "yyyy-MM-dd HH:mm" format.
     */
    public static String toDateString(LocalDateTime ldt) {
        return ldt.format(FORMATTER);
    }
}
