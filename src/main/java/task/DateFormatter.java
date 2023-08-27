package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateFormatter {
    public static final String DEFAULT_FORMAT = "MMM d yyyy";
    public static String format(String inputString, String format) {
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(inputString);
            return date.format(DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            return inputString;
        } catch (IllegalArgumentException e) {
            return date.format(DateTimeFormatter.ofPattern(DateFormatter.DEFAULT_FORMAT));
        }
    }
}
