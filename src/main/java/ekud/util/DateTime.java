package ekud.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents either a date, time or both.
 * Wraps the {@link java.time.LocalDate}, {@link java.time.LocalTime} and
 * {@link java.time.LocalDateTime} classes available in the standard library and
 * represents only one of them at a given time.
 */
public final class DateTime {
    private static DateTimeFormatter PARSE_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static DateTimeFormatter PARSE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter PARSE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private static DateTimeFormatter DISPLAY_DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static DateTimeFormatter DISPLAY_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

    private final LocalDate date;
    private final LocalTime time;
    private final LocalDateTime dateTime;

    private DateTime(LocalDate date) {
        this.date = date;
        time = null;
        dateTime = null;
    }

    private DateTime(LocalTime time) {
        date = null;
        this.time = time;
        dateTime = null;
    }

    private DateTime(LocalDateTime dateTime) {
        date = null;
        time = null;
        this.dateTime = dateTime;
    }

    /**
     * Parses the given string to return a date, time or both.
     * 
     * @param data The raw string to parse as a date, time or both.
     */
    public static DateTime parse(String data) {
        try {
            return new DateTime(LocalDate.parse(data, PARSE_DATE_FORMATTER));
        } catch (DateTimeParseException dateError) {
            try {
                return new DateTime(LocalTime.parse(data, PARSE_TIME_FORMATTER));
            } catch (DateTimeParseException timeError) {
                return new DateTime(LocalDateTime.parse(data, PARSE_DATE_TIME_FORMATTER));
            }
        }
    }

    /**
     * Returns the time stored, or null if only a date is stored.
     * 
     * @return The time.
     */
    public LocalTime getTime() {
        if (time != null) {
            return time;
        } else if (dateTime != null) {
            return dateTime.toLocalTime();
        } else {
            return null;
        }
    }

    /**
     * Returns the date stored, or null if only a time is stored.
     * 
     * @return The date.
     */
    public LocalDate getDate() {
        if (date != null) {
            return date;
        } else if (dateTime != null) {
            return dateTime.toLocalDate();
        } else {
            return null;
        }
    }

    /**
     * Returns the date and time stored, the date stored combined with the current
     * time, or the time stored combined with the current date.
     * 
     * @return The date and time.
     */
    public LocalDateTime getDateTime() {
        if (dateTime != null) {
            return dateTime;
        } else if (date != null) {
            return date.atStartOfDay();
        } else {
            return time.atDate(LocalDate.now());
        }
    }

    @Override
    public String toString() {
        if (date != null) {
            return DISPLAY_DATE_FORMATTER.format(date);
        } else if (time != null) {
            return DISPLAY_TIME_FORMATTER.format(time);
        } else {
            return DISPLAY_DATE_TIME_FORMATTER.format(dateTime);
        }
    }
}
