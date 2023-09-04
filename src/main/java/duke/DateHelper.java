package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    private final static String dateFormat = "dd-MM-yyyy HHmm";

    /**
     * Returns LocalDateTime from a String of format dd-MM-yyyy HHmm
     *
     * @param datetime String input
     * @return LocalDateTime
     */
    public static LocalDateTime parse(String datetime) {
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Returns a different datetime format as a String from inputted format.
     *
     * @param parsedTime Parsed LocalDateTime from user input
     * @return Different String datetime format
     */
    public static String format(LocalDateTime parsedTime) {
        return parsedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mma"));
    }

    /**
     * Returns datetime as a String in the format dd-MM-yyyy HHmm to be used to save into data file.
     *
     * @param parsedTime Parsed LocalDateTime from user input
     * @return String format in dd-MM-yyyy HHmm
     */
    public static String saveFormat(LocalDateTime parsedTime) {
        return parsedTime.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
