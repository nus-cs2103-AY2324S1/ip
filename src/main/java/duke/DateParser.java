package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/** Abstraction to parse dates */
public class DateParser {

    /** Parses dates and ensure is in the right format.
     *
     * @param dateInput Date to be parsed.
     * @return LocalDate object if input is in correct format.
     */
    public static LocalDate parseDate(String dateInput) {
        try {
            LocalDate date = LocalDate.parse(dateInput.trim(), DateTimeFormatter.ISO_LOCAL_DATE);
            return date;
        } catch (DateTimeParseException error) {
            throw new IllegalArgumentException("OOPS!!! Date format have to be in yyyy-mm-dd");
        }
    }
}

