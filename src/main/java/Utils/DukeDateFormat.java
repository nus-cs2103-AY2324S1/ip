package Utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class DukeDateFormat {

    public static LocalDate stringToDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput);
            return date;
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public static String dateToString(LocalDate date) {
        String day = String.valueOf(date.getDayOfMonth());
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String year = String.valueOf(date.getYear());

        return month + " " + day + " " + year;
    }

}
