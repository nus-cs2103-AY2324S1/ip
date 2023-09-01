package util;


import exception.TimeUtilException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class TimeUtil {
    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"), // 2023-05-28 1800
            DateTimeFormatter.ofPattern("yyyyMMdd HHmm"), // 20230528 1800
            DateTimeFormatter.ISO_LOCAL_DATE, // yyyy-mm-dd
            DateTimeFormatter.BASIC_ISO_DATE, // yyyymmdd
            DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH), // 1 Jan 2023
            DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH), // 1 January 2023
            DateTimeFormatter.ofPattern("yyyy-MM"), // 2023-05
            DateTimeFormatter.ofPattern("yyyyMM"), // 202305
    };

    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");

    private TimeUtil() {}

    public static LocalDateTime parseDateTimeString(String input) throws TimeUtilException {
        LocalDateTime dateTime = handleSpecialStrings(input);
        if (dateTime != null) {
            return dateTime;
        }
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (DateTimeParseException ignored) {
                // Try next formatter.
            }
        }
        // If input is not in a recognised format.
        throw new TimeUtilException("Invalid date format: " + input);
    }

    public static String formatLocalDateTime(LocalDateTime localDate) {
        return localDate.format(DISPLAY_FORMATTER);
    }

    private static LocalDateTime handleSpecialStrings(String input) {
        switch (input.toLowerCase()) {
            case "today":
                return LocalDateTime.now();
            case "tomorrow":
                return LocalDateTime.now().plusDays(1);
            default:
                return null;
        }
    }
}
