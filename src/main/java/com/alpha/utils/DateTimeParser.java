package com.alpha.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Date time parser utility class.
 */
public class DateTimeParser {

    private DateTimeParser() {
    }

    /**
     * Parse date time string from yyyy-MM-dd HH:mm into a LocalDateTime object.
     *
     * @param text The text to be parsed.
     * @return The LocalDateTime object.
     */
    public static LocalDateTime parseDateTimeString(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(text, formatter);
    }

    /**
     * Parse LocalDateTime object into a string to be displayed.
     *
     * @param localDateTime The LocalDateTime object.
     * @return The parsed string.
     */
    public static String parseDateTimeObjectToDisplay(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Parse LocalDateTime object into a string to be stored into the local storage file.
     *
     * @param localDateTime The LocalDateTime object.
     * @return The parsed string.
     */
    public static String parseDateTimeObjectToStore(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
