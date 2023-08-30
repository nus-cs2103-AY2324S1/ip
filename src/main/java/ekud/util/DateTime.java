package ekud.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
