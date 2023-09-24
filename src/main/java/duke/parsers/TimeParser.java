package duke.parsers;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.ErrorMessages;
import duke.exceptions.UnknownCommandException;

/**
 * A TimeHandler class that encapsulates all the methods that related to handling the time related string.
 */
public class TimeParser {
    //@@author Yufannn-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date format.
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
    //@@author

    private static final String[] ACCEPTABLE_DATE_FORMATS = {
        "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
        "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy"
    };

    public static LocalDate parseToLocalDate(String date) throws UnknownCommandException {
        for (String dateFormat : ACCEPTABLE_DATE_FORMATS) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
            } catch (Exception e) {
                // Go to the next dateFormat
            }
        }
        throw new UnknownCommandException(ErrorMessages.INVALID_DATE_ERROR);
    }

    public static LocalDateTime parseToLocalDateTime(String date) throws UnknownCommandException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                        DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }
        throw new UnknownCommandException(ErrorMessages.INVALID_DATETIME_ERROR);
    }

    public static String humanReadableFormat(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
    //@@author
}
