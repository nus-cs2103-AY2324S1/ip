package duke.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.UnknownCommandException;
import duke.exceptions.ErrorMessages;
public class TimeParser {
    private static final String[] DATETIME_FORMATS = {
            "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
            "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
            "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
            "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
            "yyyy-MM-dd HHmm", "yyyy-MM-dd HH:mm",
            "dd MMM yyyy HHmm", "dd MMM yyyy HH:mm",
            "MMM dd, yyyy HHmm", "MMM dd, yyyy HH:mm",
            "dd-MM-yyyy HH:mm", "dd.MM.yyyy HH:mm",
            "dd/MM/yy HH:mm", "yyyy-MM-dd'T'HH:mmZ",
            "MMM dd yyyy hh:mm a", "yyyy-MM-dd HH:mm:ss"
    };

    private static final String[] DATE_FORMATS = {
            "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
            "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy",
            "dd/MM/yy", "yy-MM-dd", "dd.MM.yyyy"
    };
    private static final List<DateTimeFormatter> ACCEPTABLE_DATE_FORMATTERS = Arrays.stream(DATE_FORMATS)
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());

    private static final List<DateTimeFormatter> ACCEPTABLE_DATETIME_FORMATTERS = Arrays.stream(DATETIME_FORMATS)
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());

    public static LocalDate parseToLocalDate(String date) throws UnknownCommandException {
        for (DateTimeFormatter formatter : ACCEPTABLE_DATE_FORMATTERS) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
            }
        }
        throw new UnknownCommandException(ErrorMessages.INVALID_DATE_ERROR);
    }

    public static LocalDateTime parseToLocalDateTime(String date) throws UnknownCommandException {
        for (DateTimeFormatter formatter : ACCEPTABLE_DATETIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
            }
        }
        throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
    }
}
