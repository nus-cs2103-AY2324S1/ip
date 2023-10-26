package duke;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Represents a date parser that parses a date string into a LocalDate object.
 */
public class DateParser {

    private static final Pattern DATE_PATTERN_DASH = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern DATE_PATTERN_SLASH = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4} .*\\d{4}$");

    /**
     * Checks if the date is in the format of YYYY-MM-DD.
     * @param date  The String date to be checked.
     * @return true if the date is in the format of YYYY-MM-DD.
     */
    private static boolean isDashedDate(String date) {
        return DATE_PATTERN_DASH.matcher(date).matches();
    }

    /**
     * Checks if the date is in the format of DD/MM/YYYY HHmm.
     * @param date  The String date to be checked.
     * @return true if the date is in the format of DD/MM/YYYY HHmm.
     */
    private static boolean isSlashedDate(String date) {
        return DATE_PATTERN_SLASH.matcher(date).matches();
    }

    /**
     * Checks if the date is in either of the specified format.
     * @param date  The String date to be checked.
     * @return true if the date is in either of the specified format.
     */
    public static boolean isEitherDate(String date) {
        return isDashedDate(date) || isSlashedDate(date);
    }

    /**
     * Formats the string in slashed format into a LocalDate.
     * @param date The String date to be formatted.
     * @return The LocalDate object.
     */
    private static LocalDate formatSlashedDate(String date) {
        String[] dateParts = date.split("/");
        int day = dateParts[0].length() == 1 ? Integer.valueOf("0" + dateParts[0]) : Integer.valueOf(dateParts[0]);
        int month = dateParts[1].length() == 1 ? Integer.valueOf("0" + dateParts[1]) : Integer.valueOf(dateParts[1]);
        String[] yearParts = dateParts[2].split(" ");

        return LocalDate.of(Integer.valueOf(yearParts[0]), month, day);
    }

    /**
     * Formats the string in dashed format into a LocalDate.
     * @param date The String date to be formatted.
     * @return The LocalDate object.
     */
    private static LocalDate formatDashedDate(String date) {
        String[] dateParts = date.split("-");
        return LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]),
                Integer.parseInt(dateParts[2]));
    }

    /**
     * Formats strings in either of the two verified formats into a LocalDate.
     * @param date The String date to be formatted.
     * @return The LocalDate object.
     */
    public static LocalDate formatDate(String date) {
        if (isDashedDate(date)) {
            return formatDashedDate(date);
        } else {
            return formatSlashedDate(date);
        }
    }


}
