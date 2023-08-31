package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Class to format the date.
 */
public class DukeDateFormat {

    /**
     * Converts a string to LocalDate.
     * @param dateInput String representation of date.
     * @return The LocalDate representation of date.
     */
    public static LocalDate stringToDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput);
            return date;
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Converts a date to String.
     * @param date LocalDate representation of date.
     * @return The string representation of date.
     */
    public static String dateToString(LocalDate date) {
        String day = String.valueOf(date.getDayOfMonth());
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String year = String.valueOf(date.getYear());

        return month + " " + day + " " + year;
    }

}
