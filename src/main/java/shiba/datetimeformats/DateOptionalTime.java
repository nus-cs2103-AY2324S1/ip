package shiba.datetimeformats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import shiba.exceptions.InvalidCommandException;

/**
 * Represents a date with an optional time.
 */
public class DateOptionalTime implements Comparable<DateOptionalTime> {
    private static final String DATE_DISPLAY_FORMAT = "d LLL yyyy";
    private static final String DATE_TIME_DISPLAY_FORMAT = "d LLL yyyy h:mma";

    private LocalDate date;
    private LocalDateTime dateTime;

    /**
     * Constructs a DateOptionalTime object from a string. Accepts formats are the default ISO,
     * default ISO with T replaced as space, and default ISO date only.
     *
     * @param dateOptionalTime The string to parse.
     * @throws InvalidCommandException If the string is not in any of the accepted formats.
     */
    public DateOptionalTime(String dateOptionalTime) throws InvalidCommandException {
        String strippedString = dateOptionalTime.strip();

        // Try with the default ISO format first
        try {
            dateTime = LocalDateTime.parse(strippedString);
            return;
        } catch (DateTimeParseException ignored) {
            // Ignore exception, try to parse with next format
        }

        // Try with the T removed
        try {
            dateTime = LocalDateTime.parse(strippedString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return;
        } catch (DateTimeParseException ignored) {
            // Ignore exception, try to parse with next format
        }

        // Try with default ISO date only format
        try {
            date = LocalDate.parse(strippedString);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date! Must be a valid date in the format"
                    + " YYYY-MM-DD or YYYY-MM-DD HH:mm.");
        }
    }

    /**
     * Gets a readable string representation (day month year hour min am/pm)
     * of a DateOptionalTime object for display.
     *
     * @return The string representation of the DateOptionalTime object.
     */
    public String getDisplayRepr() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_DISPLAY_FORMAT));
        }
        return date.format(DateTimeFormatter.ofPattern(DATE_DISPLAY_FORMAT));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (dateTime != null) {
            return dateTime.toString();
        }
        return date.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(DateOptionalTime o) {
        // Case 1: Both are dates
        if (date != null && o.date != null) {
            return date.compareTo(o.date);
        }

        // Case 2: Both are datetimes
        if (dateTime != null && o.dateTime != null) {
            return dateTime.compareTo(o.dateTime);
        }

        // Case 3: One is a date, other is datetime - convert datetime to date
        if (date != null && o.dateTime != null) {
            return date.compareTo(o.dateTime.toLocalDate());
        } else if (dateTime != null && o.date != null) {
            return dateTime.toLocalDate().compareTo(o.date);
        }

        assert false;
        return 0;
    }
}
