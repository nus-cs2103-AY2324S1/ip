package shiba.datetimeformats;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import shiba.exceptions.InvalidCommandException;

/**
 * Represents a date with an optional time.
 */
public class DateOptionalTime {
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
        // Try with the default ISO format first
        try {
            dateTime = LocalDateTime.parse(dateOptionalTime);
            return;
        } catch (DateTimeParseException ignored) {
            // Ignore exception, try to parse with next format
        }

        // Try with the T removed
        try {
            dateTime = LocalDateTime.parse(dateOptionalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return;
        } catch (DateTimeParseException ignored) {
            // Ignore exception, try to parse with next format
        }

        // Try with default ISO date only format
        try {
            date = LocalDate.parse(dateOptionalTime);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format! Date must be in the format"
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
            return dateTime.format(DateTimeFormatter.ofPattern("d LLL yyyy h:mma"));
        }

        return date.format(DateTimeFormatter.ofPattern("d LLL yyyy"));
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
}
