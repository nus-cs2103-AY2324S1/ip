package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

/**
 * Manages DateTimeFormatters to be used in the application.
 */

public class DtFormat {
    private static ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter outDateFormat;

    /**
     * Constructor for the DtFormat class, creating severaL default DateTimeFormatters to
     * be used.
     */
    public DtFormat() {
        formatters = new ArrayList<>();
        DateTimeFormatter f1 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d[ H:m]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        DateTimeFormatter f2 = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy[ Hmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();


        formatters.add(f1);
        formatters.add(f2);
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy H"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));
        outDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    }

    /**
     * Returns a DateTimeFormatter to format output.
     *
     * @returns DateTimeFormatter to format output
     */
    public DateTimeFormatter getOutFormatter() {
        return outDateFormat;
    }

    /**
     * Attempts to convert a string into a local datetime, given a predefined set of formatters.
     * If unable to do so, returns null.
     *
     * @param dateString string to be converted into datetime
     * @return datetime version of string
     */
    public static LocalDateTime parseDateString(String dateString) {
        LocalDateTime dt = null;
        for (DateTimeFormatter fmt : formatters) {
            try {
                dt = LocalDateTime.parse(dateString, fmt);
                break;
            } catch (DateTimeParseException e) {
                dt = null;
            }
        }
        return dt;
    }
}
