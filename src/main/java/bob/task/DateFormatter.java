package bob.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Formats dates into different patterns.
 */
public class DateFormatter {
    public static final String DEFAULT_FORMAT = "MMM d yyyy";

    /**
     * Formats input String into required pattern if input String abides by
     * Java LocalDate format. Otherwise, returns original input String.
     *
     * @param inputString Raw String input
     * @param format Required pattern for date format conversion
     * @return Date of required pattern, original String may be returned (based on input)
     */
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
