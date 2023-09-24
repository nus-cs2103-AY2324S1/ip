package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

/**
 * A TimeParser class that encapsulates all methods related to handling time-related strings.
 */
public class TimeParser {
    private static final String[] ACCEPTABLE_DATETIME_FORMATS = {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
        "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
        "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
        "yyyy/MM/dd'T'HH:mm", "yyyy-MM-dd HHmm",
        "yyyy-MM-dd HH:mm", "dd MMM yyyy HHmm",
        "dd MMM yyyy HH:mm", "MMM dd, yyyy HHmm",
        "MMM dd, yyyy HH:mm", "dd-mm-yyyy HHmm"
    };

    private static final String[] ACCEPTABLE_DATE_FORMATS = {
        "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
        "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy"
    };

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string to parse.
     * @return The LocalDate object representing the parsed date.
     * @throws UnknownCommandException if the date string cannot be parsed.
     */
    public static LocalDate parseToLocalDate(String date) throws UnknownCommandException {
        Optional<LocalDate> result = Arrays.stream(ACCEPTABLE_DATE_FORMATS)
                .map(format -> {
                    try {
                        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(parsedDate -> parsedDate != null)
                .findFirst();

        return result.orElseThrow(() -> new UnknownCommandException(ErrorMessages.INVALID_DATE_ERROR));
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     *
     * @param date The date-time string to parse.
     * @return The LocalDateTime object representing the parsed date-time.
     * @throws UnknownCommandException if the date-time string cannot be parsed.
     */
    public static LocalDateTime parseToLocalDateTime(String date) throws UnknownCommandException {
        Optional<LocalDateTime> result = Arrays.stream(ACCEPTABLE_DATETIME_FORMATS)
                .map(format -> {
                    try {
                        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(parsedDateTime -> parsedDateTime != null)
                .findFirst();

        return result.orElseThrow(() -> new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR));
    }
}
