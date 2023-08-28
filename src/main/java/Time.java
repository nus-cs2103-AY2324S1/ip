import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Time {

    private static final String TIME_FORMAT = "d/M/yyyy HH:mm";
    private static final String DISPLAY_FORMAT = "MMM d yyyy h:ma";
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);
    protected static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern(DISPLAY_FORMAT);

    public static LocalDateTime parse(String time) throws DukeException {
        try{
            return LocalDateTime.parse(time, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException();
        }
    }
}
