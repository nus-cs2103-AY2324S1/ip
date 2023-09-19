package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class for formatting date strings.
 */
public class DateFormatter {

    /**
     * Formats the input date string based on the specified format.
     *
     * @param input  The date string to be formatted.
     * @param format The desired format pattern for the output.
     * @return The formatted date string if input is a valid date, otherwise returns the original input.
     */
    public static String format(String input, String format) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern(format));
        } catch (DateTimeParseException e) {
            return input;
        }
    }
}
