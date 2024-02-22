package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A formatter that provides static utility methods to process LocalDateTime and other objects into formats like
 * String.
 */
public class Formatter {

    /**
     * Formats a LocalDateTime object in the format `MMM dd yyyy, HH:mm:ss`, and returns the result as a String.
     *
     * @param d The LocalDateTime object to format as a String.
     * @return String represented by the LocalDateTime, in the format `MMM dd yyyy, HH:mm:ss`.
     */
    public static String formatDateTime(LocalDateTime d) {
        return d.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss"));
    }
}
