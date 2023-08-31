package common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateParser {
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
    
    private static final DateTimeFormatter displayFormat = 
        DateTimeFormatter.ofPattern(
            "MMM dd yyyy h:mma",
            Locale.ENGLISH
        );

    public static LocalDateTime parseDateString(String date) {
        try {
            return LocalDateTime.parse(date, parseFormat);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String toDisplayString(LocalDateTime date) {
        return date.format(displayFormat);
    }

    public static String toFileString(LocalDateTime date) {
        return date.toString();
    }
}