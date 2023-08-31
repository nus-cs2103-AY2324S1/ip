package bongo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static LocalDateTime formatDateTime(String datetime) throws BongoException {
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new BongoException(String.format("Please enter a valid datetime in the format of %s", "DD/MM/YYYY HHMM."));
        }
    }
}
