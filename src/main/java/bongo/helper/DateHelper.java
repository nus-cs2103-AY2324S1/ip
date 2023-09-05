package bongo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A utility class to help parse and format dates.
 */
public class DateHelper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static boolean hasTaskExpired(LocalDateTime datetime) {
        return datetime.isBefore(LocalDateTime.now());
    }

    /**
     * Helper method to convert LocalDateTime object into a datetime string.
     * @param datetime Datetime string.
     * @return A foramtted datetime string.
     * @throws BongoException If LocalDateTime object is invalid.
     */
    public static String convertDateTimeToString(LocalDateTime datetime) throws BongoException {
        try {
            return formatter.format(datetime);
        } catch (DateTimeParseException e) {
            throw new BongoException("There was an error converting the datetime to a string.");
        }
    }

    /**
     * Helper method to convert datetime string into a LocalDateTime object.
     * @param datetime Datetime string.
     * @return A LocalDateTime object.
     * @throws BongoException If datetime string has an invalid format.
     */
    public static LocalDateTime convertStringToDateTime(String datetime) throws BongoException {
        try {
            return LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {
            throw new BongoException(String.format("Please enter a valid datetime in the format of %s",
                    "DD/MM/YYYY HHMM."));
        }
    }
}
