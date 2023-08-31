package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    public static LocalDate parseDate(String dateInput) {
        try {

            LocalDate date = LocalDate.parse(dateInput.trim(), DateTimeFormatter.ISO_LOCAL_DATE);

            return date;
        } catch (DateTimeParseException error) {
            throw new IllegalArgumentException("OOPS!!! Date format have to be in yyyy-mm-dd");
        }
    }
}

