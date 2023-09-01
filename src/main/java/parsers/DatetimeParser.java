package parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatetimeParser {

    public static LocalDateTime parseTimeInput(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    public static String reformatTimeOutput(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
        return time.format(formatter);
    }

    public static LocalDateTime convertToLocalDateTime(String str) {
        return LocalDateTime.parse(str);
    }
}
