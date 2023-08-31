package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public abstract class DateTimeOptional {
    private static DateTimeFormatter standardDateTimeParser = DateTimeFormatter
            .ofPattern("[yyyy MM dd[ HHmm]][yyyy-MM-dd[ HHmm]][yyyy/MM/dd[ HHmm]]");

    private static DateTimeFormatter standardDateTimeDisplay = DateTimeFormatter
            .ofPattern("dd-MM-yyyy [HH:mm]");

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
