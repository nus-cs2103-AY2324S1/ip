package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
