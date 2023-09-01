package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

