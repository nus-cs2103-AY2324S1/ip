package duke.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import duke.exception.InvalidDateTimeException;


/**
 * Utility class for parsing and transforming date-time strings.
 * <p>
 * The DateParser class provides functionality for transforming date and datetime strings from
 * one format to another. The class currently supports date strings in the format "yyyy-MM-dd"
 * and datetime strings in the format "yyyy-MM-dd HHmm".
 * </p>
 */
public class DateParser {

    /**
     * Formatter for parsing input date strings in the format "yyyy-MM-dd".
     */
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Formatter for parsing input datetime strings in the format "yyyy-MM-dd HHmm".
     */
    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Formatter for producing output date strings in the format "MMM dd yyyy".
     */
    private static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Formatter for producing output datetime strings in the format "MMM dd yyyy, h:mma".
     */
    private static final DateTimeFormatter OUTPUT_DATETIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Pattern for validating input datetime strings.
     */
    private static final Pattern DATETIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{4}$");

    /**
     * Pattern for validating input date strings.
     */
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    /**
     * Transforms the input date or datetime string to a different format.
     * <p>
     * If the input string matches the datetime pattern "yyyy-MM-dd HHmm", it will be transformed to
     * "MMM dd yyyy, h:mma". If the input string matches the date pattern "yyyy-MM-dd", it will be
     * transformed to "MMM dd yyyy". If the input string doesn't match any of the known patterns,
     * an InvalidDateTimeException will be thrown.
     * </p>
     *
     * @param dateTimeStr the input date or datetime string.
     * @return a transformed date or datetime string.
     * @throws InvalidDateTimeException if the input string format is invalid.
     */
    public static String transformDateTimeFormat(String dateTimeStr) throws InvalidDateTimeException {
        if (DATETIME_PATTERN.matcher(dateTimeStr).matches()) {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, INPUT_DATETIME_FORMATTER);
            return dateTime.format(OUTPUT_DATETIME_FORMATTER);
        } else if (DATE_PATTERN.matcher(dateTimeStr).matches()) {
            LocalDate date = LocalDate.parse(dateTimeStr, INPUT_DATE_FORMATTER);
            return date.format(OUTPUT_DATE_FORMATTER);
        } else {
            throw new InvalidDateTimeException();
        }
    }
}
