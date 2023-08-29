package dook.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

/**
 * This class contains utilities for manipulating local dates and strings.
 */
/**
 * Allows for parsing of multiple date formats.
 */
public class TimeProcessor {
    public static final DateTimeFormatter DEFAULT_PATTERN = DateTimeFormatter.ofPattern("[dd-MM-yyyy]");
    private static final DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                    "[MM/dd/yyyy]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"
                    + "[MM-dd-yyyy]" + "[dd/MM/yyyy]" + "[yyyy/MM/dd]"));
    private static final DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

    /**
     * Converts a string to a local date.
     * @param str Input string.
     * @return Local date from string.
     */
    public static LocalDate getLocalDateFromString(String str) {
        return LocalDate.parse(str, dateTimeFormatter);
    }

    /**
     * Converts a local date to a string.
     * @param localDate Input local date.
     * @return String from local date.
     */
    public static String getStringFromLocalDate(LocalDate localDate) {
        return localDate.format(TimeProcessor.DEFAULT_PATTERN);
    }
}
