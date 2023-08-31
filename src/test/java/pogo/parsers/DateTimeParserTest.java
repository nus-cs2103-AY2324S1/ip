package pogo.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeParserTest {
    @Test
    public void parse_dateTime_success() {
        LocalDateTime expected = LocalDateTime.of(2020, 1, 1, 12, 0);
        assertEquals(expected, DateTimeParser.parse("2020-01-01 12:00"));
    }

    @Test
    public void parse_timeAmPm_success() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expected = LocalDateTime.of(today.getYear(),
            today.getMonth(), today.getDayOfMonth(), 12, 0);
        String[] cases = {"12:00pm", "12:00 PM", "12:00 p.m.", "12:00 P.M."};
        for (String timeString : cases) {
            assertEquals(expected, DateTimeParser.parse(timeString));
        }
    }

    @Test
    public void parse_twentyFourHour_success() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expected = LocalDateTime.of(today.getYear(),
            today.getMonth(), today.getDayOfMonth(), 23, 59);
        assertEquals(expected, DateTimeParser.parse("23:59"));
    }

    @Test
    public void parse_invalidDateTimeFormat_throwsDateTimeParseException() {
        String[] cases = {"2020-01-01 12:00 am", "2020-01-01 12:00pm", "2020-01-01 12:00 AM",
            "2020-01-01 12:00 PM", "2020-01-01 12:00 a.m.", "2020-01-01 12:00 p.m.",
            "2020-01-01 12:00 A.M.", "2020-01-01 12:00 P.M."};
        for (String timeString : cases) {
            assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse(timeString));
        }
    }

    @Test
    public void parse_emptyString_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse(""));
    }

    @Test
    public void parse_null_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeParser.parse(null));
    }
}
