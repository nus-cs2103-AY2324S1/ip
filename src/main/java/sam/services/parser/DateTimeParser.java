package sam.services.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Converts date and time to a standard format (MMM dd yyyy hh:mm a).
 */
public class DateTimeParser {
    private static List<DateTimeFormatter> dateFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            DateTimeFormatter.ofPattern("yyyy-M-d")
    );

    private static List<DateTimeFormatter> dateTimeFormatters = Arrays.asList(
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
            DateTimeFormatter.ofPattern("d-M-yyyy HHmm"),
            DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
    );

    public static LocalDateTime parseDate(String input) {
        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(input, formatter);
            } catch (Exception e) {
                // Try the next formatter if parsing fails
            }
        }

        for (DateTimeFormatter formatter : dateFormatters) {
            try {
                return LocalDateTime.of(LocalDate.parse(input, formatter), LocalTime.MIDNIGHT);
            } catch (Exception e) {
                // Try the next formatter if parsing fails
            }
        }

        throw new IllegalArgumentException("Invalid date format: " + input);
    }

}
