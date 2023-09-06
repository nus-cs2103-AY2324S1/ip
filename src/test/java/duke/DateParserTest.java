package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateParserTest {

    @Test
    public void dateParse_correctDateFormat() {
        LocalDate date = DateParser.parseDate("2023-12-23");
        assertEquals("2023-12-23", date.toString());
    }

    @Test
    public void dateParse_wrongDateFormat_errorMessageThrown() {
        try {
            LocalDate date = DateParser.parseDate("2023-13-1");
        } catch (IllegalArgumentException error) {
            assertEquals("OOPS!!! Date format have to be in yyyy-mm-dd", error.getMessage());
        }
    }
}

