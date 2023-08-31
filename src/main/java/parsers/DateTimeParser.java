package parsers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeParser {
    public static LocalDateTime parseDateTime(String dateTime) {
        //2019-10-15 1800
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, format);
    }

    public static LocalDateTime readTasksParser(String dateTime) {
        //2019-10-15 1800
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return LocalDateTime.parse(dateTime, format);
    }
}
