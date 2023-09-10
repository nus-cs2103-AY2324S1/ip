package bongo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * A utility class to help parse and format dates.
 */
public class DateHelper {
    private static final String datePattern = "dd/MM/yyyy";
    private static final String dateTimePattern = "d/M/yyyy HHmm";

    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern, Locale.ENGLISH);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);

    public static boolean hasTaskExpired(LocalDateTime datetime) {
        return datetime.isBefore(LocalDateTime.now());
    }

    /**
     * Helper method to convert LocalDateTime object into a datetime string.
     * @param datetime Datetime string.
     * @return A formatted datetime string.
     * @throws BongoException If LocalDateTime object is invalid.
     */
    public static String convertDateTimeToString(LocalDateTime datetime) throws BongoException {
        try {
            return dateTimeFormatter.format(datetime);
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
            return LocalDateTime.parse(datetime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new BongoException(String.format("Please enter a valid datetime in the format of %s",
                    "DD/MM/YYYY HHMM."));
        }
    }
}
