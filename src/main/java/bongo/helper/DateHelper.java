package bongo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateHelper {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Helper method to convert LocalDateTime object to a nicely formatted datetime string.
     * @param datetime Datetime string.
     * @return A formatted datetime string.
     * @throws BongoException If datetime string has an invalid format.
     */
    public static LocalDateTime formatDateTime(String datetime) throws BongoException {
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new BongoException(String.format("Please enter a valid datetime in the format of %s", "DD/MM/YYYY HHMM."));
        }
    }
}
