package duke.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Class that handles time formating
 *
 * @author marioalvaro
 */
public class Time {
    /**
     * Convert string of time into LocalDateTime.
     *
     * @param timeString the input string
     * @return A LocalDateTime representation of the string
     */
    public static LocalDateTime toLocalDateTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        return time;
    }

    /**
     * Formatted string representation fo the LocalDateTime.
     *
     * @param time the LocalDateTime object
     * @return The String representation
     */
    public static String toString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Formatted string representation of the LocalDateTime to be stored.
     *
     * @param time the LocalDateTime object
     * @return The String representation for storage
     */
    public static String toDataString(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
