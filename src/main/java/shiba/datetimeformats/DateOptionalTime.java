package shiba.datetimeformats;

import shiba.exceptions.InvalidCommandException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateOptionalTime {
    private LocalDate date;
    private LocalDateTime dateTime;

    public DateOptionalTime(String dateOptionalTime) throws InvalidCommandException {
        // Try with the default ISO format first
        try {
            dateTime = LocalDateTime.parse(dateOptionalTime);
            return;
        } catch (DateTimeParseException ignored) {}

        // Try with the T removed
        try {
            dateTime = LocalDateTime.parse(dateOptionalTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            return;
        } catch (DateTimeParseException ignored) {}

        // Try with default ISO date only format
        try {
            date = LocalDate.parse(dateOptionalTime);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date format! Date must be in the format YYYY-MM-DD or YYYY-MM-DD HH:mm.");
        }
    }

    /**
     * Gets a readable string representation (day month year hour min am/pm)
     * of a LocalDateTime object for display.
     *
     * @return The string representation of the DateOptionalTime object.
     */
    public String getDisplayRepr() {
        if (dateTime != null) {
            return dateTime.format(DateTimeFormatter.ofPattern("d LLL yyyy h:mma"));
        }

        return date.format(DateTimeFormatter.ofPattern("d LLL yyyy"));
    }

    @Override
    public String toString() {
        if (dateTime != null) {
            return dateTime.toString();
        }

        return date.toString();
    }
}
