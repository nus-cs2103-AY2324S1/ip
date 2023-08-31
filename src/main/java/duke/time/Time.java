package duke.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static LocalDateTime toLocalDateTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        return time;
    }

    public static String toString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public static String toDataString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
