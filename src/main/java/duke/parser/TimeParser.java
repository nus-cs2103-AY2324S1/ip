package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {

    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    public static LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString, inputFormat);
    }

    public static String returnTime(LocalDateTime dateTime) {
        return dateTime.format(outputFormat);
    }

    public static String toSaveString(LocalDateTime dateTime) {
        return dateTime.format(inputFormat);
    }

}
