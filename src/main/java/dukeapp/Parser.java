package dukeapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Contains utilities to parse user input.
 */
public class Parser {
    private static final List<String> dateTimeFormats = Arrays.asList(
            "d/M/yyyy", "d/M/yyyy HHmm",
            "d/MM/yyyy", "d/MM/yyyy HHmm",
            "dd/M/yyyy", "dd/M/yyyy HHmm",
            "dd/MM/yyyy", "dd/MM/yyyy HHmm",
            "yyyy-M-d", "yyyy-M-d HHmm",
            "yyyy-M-dd", "yyyy-M-dd HHmm",
            "yyyy-MM-d", "yyyy-MM-d HHmm",
            "yyyy-MM-dd", "yyyy-MM-dd HHmm"
    );

    public static LocalDateTime parseDate(String dateTimeString) {
        for (String format : dateTimeFormats) {
            try {
                if (format.contains("HHmm")) {
                    return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
                } else {
                    // input without specifying time will default to 0000
                    LocalDate date = LocalDate.parse(dateTimeString,
                            DateTimeFormatter.ofPattern(format));
                    return LocalDateTime.of(date, LocalTime.MIDNIGHT);
                }
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new DateTimeParseException(String.format(DukeConstants.ERROR_MESSAGE,
                String.format(DukeConstants.INVALID_DATE_TIME_FORMAT, dateTimeString)),
                dateTimeString, 0);
    }
}
