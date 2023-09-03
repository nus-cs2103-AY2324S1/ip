package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeValidator {
    private DateTimeFormatter formatter;

    public DateTimeValidator(String acceptedFormat) {
        this.formatter = DateTimeFormatter.ofPattern(acceptedFormat);
    }

    boolean validateDate(String date) {
        try {
            formatter.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}