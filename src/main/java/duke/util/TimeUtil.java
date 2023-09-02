package duke.util;


import duke.exception.TimeUtilException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class TimeUtil {
    private static final DateTimeFormatter[] DATE_TIME_FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // 2023-05-28 1800
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm")  // 20230528 1800
    };

    private static final DateTimeFormatter[] DATE_ONLY_FORMATTERS = {
            DateTimeFormatter.ISO_LOCAL_DATE, // yyyy-mm-dd
            DateTimeFormatter.BASIC_ISO_DATE, // yyyymmdd
            DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH), // 1 Jan 2023
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH), // 1 January 2023
    };

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private TimeUtil() {}

    public static LocalDateTime parseDateTimeString(String input) throws TimeUtilException {
        LocalDateTime dateTime = handleSpecialStrings(input);
        if (dateTime != null) {
            return dateTime;
        }
        // Attempt to parse with date-time formatters first
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next formatter.
            }
        }

        // If not parsed yet, try date-only formatters and combine with current time.
        for (DateTimeFormatter formatter : DATE_ONLY_FORMATTERS) {
            try {
                LocalDate parsedDate = LocalDate.parse(input, formatter);
                return LocalDateTime.of(parsedDate, LocalDateTime.now().toLocalTime());
            } catch (DateTimeParseException ignored) {
                // Try next formatter.
            }
        }
        // If input is not in a recognised format.
        throw new TimeUtilException(getHelpMessage());
    }

    public static String formatLocalDateTime(LocalDateTime localDate) {
        return localDate.format(DISPLAY_FORMATTER);
    }

    public static String getHelpMessage() {
        return "Invalid date format! Please use one of the following formats:" +
                "\n- yyyy-MM-dd HHmm (e.g. 2023-05-28 1800)" +
                "\n- yyyyMMdd HHmm (e.g. 2023-05-28 1800)" +
                "\n- yyyy-MM-dd (e.g. 2023-05-28)" +
                "\n- yyyymmdd (e.g. 20230528)" +
                "\n- d MMM yyyy (e.g. 1 Jan 2023)" +
                "\n- d MMMM yyyy (e.g. 1 January 2023)" +
                "\nOr use special terms like:" +
                "\n- today" +
                "\n- tomorrow";
    }

    private static LocalDateTime handleSpecialStrings(String input) {
        switch (input.toLowerCase()) {
            case "today":
                return LocalDateTime.now().withHour(23).withMinute(59);
            case "tomorrow":
                return LocalDateTime.now().plusDays(1).withHour(23).withMinute(59);
            default:
                return null;
        }
    }
}
