package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Time class is used to parse the date and time from
 * {@code String} to {@code LocalDateTime} or {@code LocalDate} object.
 */
public class Time {

    private static final String DATE_FORMAT = "d/M/yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATE_DISPLAY_FORMAT = "d MMM yyyy";
    private static final String TIME_DISPLAY_FORMAT = "h:mma";
    public static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
    public static final DateTimeFormatter DATE_TIME_DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT + ", " + TIME_DISPLAY_FORMAT);
    public static final DateTimeFormatter DATE_DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT);

    /**
     * Parses the date and time from {@code String} to {@code LocalDateTime} object.
     * <p>If the format is wrong, it will throw an exception.</p>
     *
     * @param time The string to be parsed.
     * @return The {@code LocalDateTime} object.
     * @throws DukeException If the format is wrong.
     */
    public static LocalDateTime parseDateTime(String time) throws DukeException {
        try {
            return LocalDateTime.parse(time, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.connectTwoLine("OOPS!!! The date and time format is invalid.",
                    String.format("Please use the format: \"%s %s\"", DATE_FORMAT, TIME_FORMAT)));
        }
    }

    /**
     * Parses the date from {@code String} to {@code LocalDate} object.
     * <p>If the format is wrong, it will throw an exception.</p>
     *
     * @param time The string to be parsed.
     * @return The {@code LocalDate} object.
     * @throws DukeException If the format is wrong.
     */
    public static LocalDate parseDate(String time) throws DukeException {
        try {
            return LocalDate.parse(time, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.connectTwoLine("OOPS!!! The date format is invalid.",
                    String.format("Please use the format: \"%s\"", DATE_FORMAT)));
        }
    }
}
