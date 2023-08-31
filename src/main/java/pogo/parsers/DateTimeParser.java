package pogo.parsers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Parses a datetime string into a datetime object.
 */
public class DateTimeParser {
    /**
     * Regex pattern for a datetime string.
     * Examples: 2020-01-01 12:00, 2020-01-01 12:00 am, 2020-01-01 12:00pm, 2020-01-01 12:00 AM,
     */
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile(
            "(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<time>\\d{2}:\\d{2})");

    /**
     * Regex pattern for a time string in twenty-four-hour format.
     * Examples: 12:00
     */
    private static final Pattern TIME_TWENTYFOUR_PATTERN = Pattern.compile(
            "(?<time>\\d{2}:\\d{2})");

    /**
     * Regex pattern for a time string with am/pm.
     * Examples: 12:00am, 12:00pm, 12:00 AM, 12:00 PM, 12:00 a.m., 12:00 p.m., 12:00 A.M., 12:00 P.M.
     */
    private static final Pattern TIME_AM_PM_PATTERN = Pattern.compile(
            "(?<time>\\d{1,2}:\\d{2})\\s*(?<amPm>am|pm|AM|PM|a\\.m\\.|p\\.m\\.|A\\.M\\.|P\\.M\\.)");

    private static final String ERROR_MESSAGE = "Invalid datetime format. Only the following formats are accepted:"
            + System.lineSeparator()
            + "YYYY-MM-DD HH:MM, YYYY-MM-DD HH:MM AM/PM, HH:MM, HH:MM AM/PM, HH:MM am/pm";

    private static LocalDateTime parseDateTime(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private static LocalDateTime parseTimeTwentyFour(String timeString) {
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDate date = LocalDate.now();
        return LocalDateTime.of(date, time);
    }

    private static LocalDateTime parseTimeAmPm(String timeString) {
        timeString = timeString.toUpperCase().replaceAll("\\.", "");
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("h:mm[ ]a"));
        LocalDate today = LocalDate.now();

        return LocalDateTime.of(today, time);
    }

    /**
     * Parses the input datetime string into a LocalDateTime object.
     *
     * @param datetime Input datetime string.
     * @return LocalDateTime object representing the input datetime string.
     * @throws DateTimeParseException If the input datetime string does not match
     *                                any of the accepted formats.
     */
    public static LocalDateTime parse(String datetime) throws DateTimeParseException {
        if (datetime == null) {
            throw new DateTimeParseException(ERROR_MESSAGE, "", 0);
        }

        datetime = datetime.trim(); // Remove leading and trailing whitespace
        // DateTime string must be an exact match for one of the following patterns
        if (DATE_TIME_PATTERN.matcher(datetime).matches()) {
            return parseDateTime(datetime);
        } else if (TIME_TWENTYFOUR_PATTERN.matcher(datetime).matches()) {
            return parseTimeTwentyFour(datetime);
        } else if (TIME_AM_PM_PATTERN.matcher(datetime).matches()) {
            return parseTimeAmPm(datetime);
        } else {
            throw new DateTimeParseException(ERROR_MESSAGE, datetime, 0);
        }
    }
}
