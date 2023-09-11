package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Provides method for DateTime formatting.
 */
public class DateHelper {
    private static final String dateFormat = "dd-MM-yyyy HHmm";

    /**
     * Returns LocalDateTime from a String of format dd-MM-yyyy HHmm
     *
     * @param dateTime String input.
     * @return LocalDateTime.
     */
    public static LocalDateTime parse(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(dateFormat));
    }

    /**
     * Returns a different datetime format as a String from inputted format.
     *
     * @param parsedTime Parsed LocalDateTime from user input.
     * @return Different String datetime format.
     */
    public static String format(LocalDateTime parsedTime) {
        return parsedTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mma"));
    }

    /**
     * Returns datetime as a String in the format dd-MM-yyyy HHmm to be used to save into data file.
     *
     * @param parsedTime Parsed LocalDateTime from user input.
     * @return String format in dd-MM-yyyy HHmm.
     */
    public static String saveFormat(LocalDateTime parsedTime) {
        return parsedTime.format(DateTimeFormatter.ofPattern(dateFormat));
    }
}
