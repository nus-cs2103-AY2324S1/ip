package duke.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Common string formats used throughout the project.
 */
public class Formatter {

    /**
     * Returns shared default message for counting tasks.
     *
     * @param n Number of tasks.
     * @return The default message for that number of tasks.
     */
    public static String getTaskCount(int n) {
        return String.format("You have %d task%s in the list now.", n, n == 1 ? "" : "s");
    }

    /**
     * Converts list to be displayed as a string.
     *
     * @param arr List converted.
     * @return The list as a string.
     */
    public static <T> String stringifyList(List<T> arr) {
        List<String> enumArr = new ArrayList<>();
        int i = 1;
        for (T e : arr) {
            enumArr.add(String.format("%d. %s", i++, e.toString()));
        }
        return String.join("\n", enumArr);
    }

    /**
     * Converts date to be displayed as a string.
     *
     * @param date Date converted.
     * @return The date as a string.
     */
    public static String stringifyDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

}
