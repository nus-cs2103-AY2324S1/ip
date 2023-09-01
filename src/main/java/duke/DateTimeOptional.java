package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
/**
 * This abstract class wraps around an optional date and time value.
 */
public abstract class DateTimeOptional {
    /** Accepted format for date and times. */
    private static DateTimeFormatter standardDateTimeParser = DateTimeFormatter
            .ofPattern("[yyyy MM dd[ HHmm]][yyyy-MM-dd[ HHmm]][yyyy/MM/dd[ HHmm]]");
    /** Display format for datetime. */
    private static DateTimeFormatter standardDateTimeDisplay = DateTimeFormatter
            .ofPattern("dd-MM-yyyy [HH:mm]");
    /**
     * Parses a string into a DateTimeOptional object.
     * If the string cannot be parsed into either, it throws a DukeException.DukeDateTimeException.
     *
     * @param s The string to be parsed into a DateTimeOptional object.
     * @return A DateTimeOptional object representing the parsed date and time.
     * @throws DukeException.DukeDateTimeException If the string cannot be parsed.
     */
    public static DateTimeOptional parseDateTime(String s) throws DukeException {
        TemporalAccessor temp = standardDateTimeParser.parse(s);
        try {
            LocalDateTime dateTime = LocalDateTime.parse(s, standardDateTimeParser);
            return new DateTimeOnly(dateTime);
        } catch (DateTimeParseException e) {
        }
        try {
            LocalDate date = LocalDate.parse(s, standardDateTimeParser);
            return new DateOnly(date);
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeDateTimeException(s);
        }
    }
    /**
     * Gives a string representation of the date and time value in display format.
     *
     * @return A string representation of the date and time value in display format.
     */
    public abstract String displayText();

    private static class DateOnly extends DateTimeOptional {
        final LocalDate date;

        DateOnly(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return this.date.format(standardDateTimeParser);
        }

        @Override
        public String displayText() {
            return this.date.format(standardDateTimeDisplay);
        }
    }

    private static class DateTimeOnly extends DateTimeOptional {
        final LocalDateTime date;

        DateTimeOnly(LocalDateTime date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return this.date.format(standardDateTimeParser);
        }

        @Override
        public String displayText() {
            return this.date.format(standardDateTimeDisplay);
        }
    }
}
