import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Time {

    private static final String DATE_FORMAT = "d/M/yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATE_DISPLAY_FORMAT = "d MMM yyyy";
    private static final String TIME_DISPLAY_FORMAT = "h:ma";
    protected static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT);
    protected static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_FORMAT + " " + TIME_FORMAT);
    protected static final DateTimeFormatter DATE_TIME_DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT + " " + TIME_DISPLAY_FORMAT);
    protected static final DateTimeFormatter DATE_DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT);

    public static LocalDateTime parseDateTime(String time) throws DukeException {
        try{
            return LocalDateTime.parse(time, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException();
        }
    }

    public static LocalDate parseDate(String time) throws DukeException {
        try{
            return LocalDate.parse(time, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException();
        }
    }
}
