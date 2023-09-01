package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeParser {

    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

    /**
     * Parses a string of time into a Java DateTime.
     *
     * @param timeString the input string.
     * @return the parsed DateTime.
     */
    public static LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString, inputFormat);
    }

    /**
     * Parses a Java DateTime into plain text for display.
     *
     * @param dateTime the Java DateTime.
     * @return the formatted string.
     */
    public static String returnTime(LocalDateTime dateTime) {
        return dateTime.format(outputFormat);
    }

    /**
     * Changes a Java DateTime to the predefined input format of a date string.
     * Used for local storage.
     *
     * @param dateTime the Java DateTime.
     * @return the formatted string.
     */
    public static String toSaveString(LocalDateTime dateTime) {
        return dateTime.format(inputFormat);
    }

}
