package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for parsing date and time input strings into LocalDateTime
 * objects.
 *
 * @author Ho Khee Wei
 */
public abstract class DateTimeParser {
    /** Supported date formats */
    public static final String[] DATE_FORMATS = { "yyyy-M-d", "d-M-yyyy", "yyyy/M/d", "d/M/yyyy" };

    /** Supported time formats */
    public static final String[] TIME_FORMATS = { "HH:mm", "HH-mm", "HHmm" };

    /**
     * Parses a date and/or time string into a LocalDateTime object.
     *
     * @param input The input string representing date and/or time.
     * @return A LocalDateTime object representing the parsed date and time, or null
     *         if parsing fails.
     */
    public static LocalDateTime parse(String input) {
        String date = input;
        String time = null;
        String dateFormat = null;

        if (input.split(" ").length == 2) {
            date = input.split(" ")[0];
            time = input.split(" ")[1];
        }

        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
                LocalDate.parse(date, dateFormatter);
                dateFormat = format;
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (dateFormat != null) {
            for (String format : TIME_FORMATS) {
                String dateTimeFormat = dateFormat + " " + format;
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
                    return LocalDateTime.parse(input, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    // do nothing
                }
            }

            if (time == null) {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat)).atStartOfDay();
            }

        }

        return null;
    }
}
