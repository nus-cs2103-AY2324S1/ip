package duke.time;

import duke.exceptions.TimeParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Time {
    public static final LocalDate parseTime(String time) throws TimeParsingException {
        try {
            return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            throw new TimeParsingException("Unable to parse time: " + time);
        }
    }

    public static final String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public static final String formatTimeStoring(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
