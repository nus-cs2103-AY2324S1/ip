package exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateWrongOrderException extends HachiException {
    public DateWrongOrderException(LocalDate startDate, LocalDate endDate) {
        super(String.format("Your end date (%s) is before your start date (%s). " +
                "Are you sure you entered them in the right order?",
                endDate.format(DateTimeFormatter.ISO_DATE),
                startDate.format(DateTimeFormatter.ISO_DATE)));
    }
}