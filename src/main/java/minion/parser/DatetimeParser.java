package minion.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import minion.data.exception.MinionException;

/**
 * Represents a datetime parser.
 */
public class DatetimeParser {

    private static final DateTimeFormatter FROM_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter TO_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy h:mm a");

    /**
     * Parses a datetime string into a LocalDateTime bassed on the FROM_DATETIME_FORMATTER.
     * @param s datetime string
     * @return parsed LocalDateTime
     * @throws MinionException if fail to parse the datetime string.
     */
    public static LocalDateTime parseFromDatetime(String s) throws MinionException {
        try {
            return LocalDateTime.parse(s, FROM_DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MinionException("Fail to parse datetime!");
        }
    }

    /**
     * Parses a datetime string into a LocalDateTime bassed on the TO_DATETIME_FORMATTER.
     * @param s datetime string
     * @return parsed LocalDateTime
     * @throws MinionException if fail to parse the datetime string.
     */
    public static LocalDateTime parseToDatetime(String s) throws MinionException {
        try {
            return LocalDateTime.parse(s, TO_DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new MinionException("Fail to parse datetime!");
        }
    }

    /**
     * Converts a LocalDateTime object into a string based on TO_DATETIME_FORMATTER.
     * @param localDatetime LocalDateTime object to be parsed
     * @return formatted datetime string
     * @throws DateTimeException if unable to format datetime.
     */
    public static String convertFromDatetime(LocalDateTime localDatetime) throws DateTimeException {
        return localDatetime.format(TO_DATETIME_FORMATTER);
    }


}
