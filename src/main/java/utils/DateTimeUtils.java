package utils;

import exception.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DateTimeUtils {

    public static String localDateTimeToString(LocalDateTime dateTime) {
        // Base date format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

        // Time format
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha");

        String result = dateTime.format(dateFormatter);

        // Add time if it exists and isn't midnight
        if (!(dateTime.getHour() == 0 && dateTime.getMinute() == 0)) {
            result += " " + dateTime.format(timeFormatter).toLowerCase();
        }

        return result;
    }

    public static LocalDateTime stringToLocalDateTime(String dateTimeString) throws InvalidDateTimeException {
        List<String> patterns = Arrays.asList(
                "M/d/yyyy HHmm",
                "M/d/yyyy",
                "yyyy-MM-dd",
                "yyyy-MM-dd HHmm"
        );

        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

                if (!pattern.contains("H")) {
                    LocalDate date = LocalDate.parse(dateTimeString, formatter);
                    return date.atStartOfDay();
                }

                return LocalDateTime.parse(dateTimeString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to the next pattern
            }
        }

        throw new InvalidDateTimeException();
    }

}
