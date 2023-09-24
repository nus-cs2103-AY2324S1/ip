package duke.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.exception.TimeUtilException;

/**
 * Provides utility functions to handle and format time-related inputs.
 * <p>
 * This utility class provides multiple date and time formats for parsing,
 * which can be helpful in accommodating various user inputs.
 * </p>
 */
public class TimeUtil {
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("yyyyMMdd HHmm")
    };

    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = {
        DateTimeFormatter.ISO_LOCAL_DATE,
        DateTimeFormatter.BASIC_ISO_DATE,
        DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH),
        DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
    };

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // Private constructor to prevent instantiation
    private TimeUtil() {}

    /**
     * Parses the provided date-time string to a LocalDateTime object.
     * <p>
     * The function tries various date-time patterns to find a match.
     * If none of the patterns match, it throws a TimeUtilException.
     * </p>
     *
     * @param input The date-time string to parse.
     * @return A LocalDateTime object.
     * @throws TimeUtilException If the input string cannot be parsed.
     */
    public static LocalDateTime parseDateTimeString(String input) throws TimeUtilException {
        LocalDateTime dateTime = handleSpecialStrings(input);
        if (dateTime != null) {
            return dateTime;
        }
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // try next formatter
            }
        }
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                LocalDate parsedDate = LocalDate.parse(input, formatter);
                return LocalDateTime.of(parsedDate, LocalDateTime.now().toLocalTime());
            } catch (DateTimeParseException ignored) {
                // try next formatter
            }
        }
        throw new TimeUtilException(getHelpMessage());
    }

    /**
     * Formats a LocalDateTime object to a human-readable string.
     *
     * @param localDate The LocalDateTime object to format.
     * @return A string representation of the LocalDateTime object.
     */
    public static String formatLocalDateTime(LocalDateTime localDate) {
        return localDate.format(DISPLAY_FORMATTER);
    }

    /**
     * Returns a help message explaining valid date formats.
     *
     * @return A string containing a list of valid date formats.
     */
    public static String getHelpMessage() {
        return "Invalid date format! Please use one of the following formats:"
            + "\n- yyyy-MM-dd HHmm (e.g. 2023-05-28 1800)"
            + "\n- yyyyMMdd HHmm (e.g. 2023-05-28 1800)"
            + "\n- yyyy-MM-dd (e.g. 2023-05-28)"
            + "\n- yyyymmdd (e.g. 20230528)"
            + "\n- d MMM yyyy (e.g. 1 Jan 2023)"
            + "\n- d MMMM yyyy (e.g. 1 January 2023)"
            + "\nOr use special terms like:"
            + "\n- today"
            + "\n- tomorrow"
            + "\n- monday (for the next Monday)"
            + "\n- tuesday (for the next Tuesday)"
            + "\n- ... (similarly for other days of the week)";
    }

    /**
     * Handles special string inputs, converting them to LocalDateTime.
     * <p>
     * The function currently supports 'today' and 'tomorrow' as special strings.
     * </p>
     *
     * @param input The special string input.
     * @return A LocalDateTime representation of the input, or null if the input is not special.
     */
    private static LocalDateTime handleSpecialStrings(String input) {
        switch (input.toLowerCase()) {
        case "today":
            return LocalDateTime.now().withHour(23).withMinute(59);
        case "tomorrow":
            return LocalDateTime.now().plusDays(1).withHour(23).withMinute(59);
        case "monday":
        case "tuesday":
        case "wednesday":
        case "thursday":
        case "friday":
        case "saturday":
        case "sunday":
            return getNextDayOfWeek(DayOfWeek.valueOf(input.toUpperCase()));
        default:
            return null;
        }
    }

    /**
     * Returns the LocalDateTime of the next occurrence of the specified day of the week.
     * <p>
     * This method calculates the number of days between the current day and the desired day of the week.
     * If the desired day is the same as the current day, it returns the date of the next week's occurrence.
     * </p>
     *
     * @param desiredDay The desired day of the week as a DayOfWeek enum value.
     * @return A LocalDateTime representing the next occurrence of the desired day, with the time set to 23:59.
     */
    private static LocalDateTime getNextDayOfWeek(DayOfWeek desiredDay) {
        LocalDateTime now = LocalDateTime.now();
        int daysUntilDesired = desiredDay.getValue() - now.getDayOfWeek().getValue();
        if (daysUntilDesired <= 0) { // if today is the desired day or a later day in the week
            daysUntilDesired += 7; // get the next occurrence of the desired day
        }
        return now.plusDays(daysUntilDesired).withHour(23).withMinute(59);
    }

}
