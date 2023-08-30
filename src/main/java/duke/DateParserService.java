package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * The DateParserService class provides a method to parse dates from various formats into LocalDate objects.
 */
public class DateParserService {

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param date The date string to be parsed.
     * @return A LocalDate object representing the parsed date.
     * @throws DateTimeParseException If the input date string does not match any of the supported formats,
     *                                or if the parsing process fails.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        // Remove leading and trailing whitespace from the input date string
        date = date.strip();

        // Define a formatter with multiple patterns to accommodate different date formats
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[dd-MM-yyyy]" + "[yyyy-MM-dd]"
                        + "[MMM dd yyyy]" + "[dd MMM yyyy]" + "[dd/MM/yyyy]" + "[yyyy/MM/dd]"));
        DateTimeFormatter dateTimeFormatter = dateTimeFormatterBuilder.toFormatter();

        // Parse the date string using the defined formatter and return the resulting LocalDate object
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
