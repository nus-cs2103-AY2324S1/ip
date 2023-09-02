package todoify.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * A utility formatter and converter class to assist in formatting dates to and from the Unix epoch.
 */
public class EpochConverter {

    /**
     * Converts the given date-time string to number representing seconds since the Unix epoch.
     *
     * @param dateTimeString The date time string to convert, in ISO format of "yyyy-MM-ddThh:mm:ss".
     * @return The number of seconds since the Unix epoch of 1970-01-01 00:00:00 UTC.
     * @throws DateTimeParseException if the date-time cannot be parsed.
     */
    public static long getEpochFromIsoDateString(String dateTimeString) throws DateTimeParseException {
        DateTimeParseException cachedException;

        // Try with date and time.
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME)
                    .atZone(ZoneId.systemDefault())
                    .toEpochSecond();
        } catch (DateTimeParseException e) {
            cachedException = e;
        }

        // Otherwise, try with date only.
        try {
            return LocalDate.parse(dateTimeString, DateTimeFormatter.ISO_DATE)
                    .atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toEpochSecond();
        } catch (DateTimeParseException e) {
            // Do nothing...
        }

        // Otherwise, give up.
        throw cachedException;
    }

    /**
     * Converts seconds since the Unix epoch to a date-time string.
     *
     * @param epochSeconds The number of seconds since the Unix epoch of 1970-01-01 00:00:00 UTC.
     * @return The resulting date time string in ISO format of "yyyy-MM-ddThh:mm:ss".
     */
    public static String getIsoDateStringFromEpoch(long epochSeconds) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochSecond(epochSeconds), ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }


    /**
     * Converts seconds since the Unix epoch to a user-readable date-time string.
     *
     * @param epochSeconds The number of seconds since the Unix epoch of 1970-01-01, 00:00:00, UTC.
     * @return The resulting date time string in a user readable format.
     */
    public static String getUserReadableStringFromEpoch(long epochSeconds) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochSecond(epochSeconds), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }


}
