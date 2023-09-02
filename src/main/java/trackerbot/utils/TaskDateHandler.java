package trackerbot.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Handles Date-related methods for Task.
 * @author WZWren
 * @version Level-8
 */
public class TaskDateHandler {
    /** Suppress the constructor, as no instance should be created. **/
    private TaskDateHandler() {}

    /**
     * Converts the String into a LocalDateTime object.
     * @param input The input String to parse into a Date object.
     *              The String should be in the format: d/M/yyyy HHmm <br>
     *              The year and time are optional. If not given, the year is assumed
     *              to be the current year, and the time is assumed to be 0000.
     * @return The LocalDateTime object from the parsed String
     * @throws DateTimeParseException if the input does not match the format string
     */
    public static LocalDateTime convertInputToDate(String input) throws DateTimeParseException {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("d/M[/yyyy][ HHmm]"))
                .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDateTime.now().get(ChronoField.YEAR_OF_ERA))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
        return LocalDateTime.parse(input, format);
    }

    public static LocalDateTime convertSaveToDate(String input)
            throws DateTimeParseException, NumberFormatException {
        long epoch = Long.parseLong(input);
        return LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC);
    }

    public static String convertDateToUi(LocalDateTime date) {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("dd MMM yyyy, EEE @ hh:mma"))
                .toFormatter();
        return date.format(format);
    }
}
