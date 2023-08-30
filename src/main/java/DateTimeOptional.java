import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public abstract class DateTimeOptional {
    private static DateTimeFormatter standardDateTimeParser = DateTimeFormatter
            .ofPattern("[yyyy MM dd HHmm][yyyy MM dd]");

    public static DateTimeOptional parseDateTime(String s) throws DukeException {
        try {
            TemporalAccessor temp = standardDateTimeParser.parseBest(s);
            if (temp instanceof LocalDate date) {
                return new DateOnly(date);
            }
            if (temp instanceof LocalDateTime date) {
                return new DateTimeOnly(date);
            }
            throw new DukeException.DukeDateTimeException(s);
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeDateTimeException(s);
        }
    }

    private static class DateOnly extends DateTimeOptional {
        final LocalDate date;

        DateOnly(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return this.date.format(standardDateTimeParser);
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
    }
}
