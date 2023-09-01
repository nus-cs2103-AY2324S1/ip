package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * The DateParser class.
 * Handles parsing date strings to {@link LocalDateTime}
 * and displaying {@link LocalDateTime} as "MMM dd yyyy h:mma".
 * Example: Aug 10 2023 6:00PM.
 */
public class DateParser {
    /**
     * The {@link DateTimeFormatter} used to parse
     * date strings into {@link LocalDateTime}.
     */
    private static final DateTimeFormatter parseFormat = 
        new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ofPattern(
                "[d/M/yyyy]"
                + "[yyyy/M/d]"
                + "[d-M-yyyy]"
                + "[yyyy-M-d]"
            ))
            .append(DateTimeFormatter.ofPattern(
                "[d MMM yyyy]" + "[MMM d yyyy]", 
                Locale.ENGLISH
            ))
            .optionalStart()
            .appendPattern("[ HHmm][ Hmm]['T'HH:mm]")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    
    /**
     * The {@link DateTimeFormatter} used to display
     * {@link LocalDateTime} as a string.
     */
    private static final DateTimeFormatter displayFormat = 
        DateTimeFormatter.ofPattern(
            "MMM dd yyyy h:mma",
            Locale.ENGLISH
        );

    /**
     * Parses the string given into a {@link LocalDateTime}.
     * 
     * @param date The date string to be parsed.
     * @return A {@link LocalDateTime} instance.
     */
    public static LocalDateTime parseDateString(String date) {
        try {
            return LocalDateTime.parse(date, parseFormat);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Gets the string representation of the given {@link LocalDateTime}
     * and converts it into the format displayed by Duke.
     * 
     * @param date The {@link LocalDateTime} to be displayed.
     * @return The display string of the date given.
     */
    public static String toDisplayString(LocalDateTime date) {
        return date.format(displayFormat);
    }

    /**
     * Gets the string representation of the given {@link LocalDateTime}
     * and converts it into the format that {@link Storage} uses to
     * write to a persistent file.
     * 
     * @param date The {@link LocalDateTime} to be stored on the file.
     * @return A string that can be written to a file.
     */
    public static String toFileString(LocalDateTime date) {
        return date.toString();
    }
}