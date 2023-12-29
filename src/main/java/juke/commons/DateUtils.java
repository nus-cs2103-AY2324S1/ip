package juke.commons;

import java.time.LocalDateTime;

/**
 * Provides some utility methods for DateTime comparisons.
 */
public class DateUtils {
    /**
     * Compares two {@code LocalDateTime} objects and returns the integer describing the
     * order of the {@code LocalDateTimes}.
     *
     * @param firstDate First {@code LocalDateTime} object
     * @param secondDate Second {@code LocalDateTime} object
     * @return -1 if the first datetime is before the second datetime, 0 if they are equal and 1 if the first datetime
     *     is after the second datetime
     */
    public static int compareDateTimes(LocalDateTime firstDate, LocalDateTime secondDate) {
        return firstDate.compareTo(secondDate);
    }

    /**
     * Compares two {@code LocalDateTime} objects and checks if the first datetime is after the second datetime.
     *
     * @param firstDate First {@code LocalDateTime} object
     * @param secondDate Second {@code LocalDateTime} object
     * @return true if the first datetime is after the second datetime
     */
    public static boolean isAfter(LocalDateTime firstDate, LocalDateTime secondDate) {
        return firstDate.isAfter(secondDate);
    }
}
