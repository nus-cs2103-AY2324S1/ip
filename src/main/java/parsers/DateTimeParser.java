package parsers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    /*
     * Function to convert a String to LocalDateTime.
     *
     * @param dateTime a String to be read.
     * @return The LocalDateTime object constructed from the String.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        //2019-10-15 1800
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTime, format);
    }

    /*
     * Function to convert a String from storage.txt to LocalDateTime.
     *
     * @param dateTime a String from storage.txt
     * @return The LocalDateTime object constructed from the String.
     */
    public static LocalDateTime readTasksParser(String dateTime) {
        //2019-10-15 1800
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy ha");
        return LocalDateTime.parse(dateTime, format);
    }
}
