package duke.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDateTimeInput_invalidInput_exceptionThrown() {
        try {
            Parser.parseDateTimeInput("dd-MM-yyyy[ HHmm]");
            fail();
        } catch (DukeException e) {
            assertEquals("Please use the format \"DD-MM-YYYY [HHMM]\"", e.getMessage());
        }
    }

    @Test
    public void parseDateTimeInput_partiallyInvalidInput_exceptionThrown() {
        try {
            Parser.parseDateTimeInput("01-02-2023 a1222");
            fail();
        } catch (DukeException e) {
            assertEquals("Please use the format \"DD-MM-YYYY [HHMM]\"", e.getMessage());
        }
    }

    @Test
    public void parseDateTimeInput_validDateInput_success() {
        try {
            TemporalAccessor result = Parser.parseDateTimeInput("01-02-2023");
            assertEquals(1, result.get(ChronoField.DAY_OF_MONTH));
            assertEquals(2, result.get(ChronoField.MONTH_OF_YEAR));
            assertEquals(2023, result.get(ChronoField.YEAR));
            assertEquals(true, result instanceof LocalDate);
        } catch (DukeException e) {
            assertNotEquals("Please use the format \"DD-MM-YYYY [HHMM]\"", e.getMessage());
            fail();
        }
    }

    @Test
    public void parseDateTimeInput_validDateTimeInput_success() {
        try {
            TemporalAccessor result = Parser.parseDateTimeInput("01-02-2023 1222");
            assertEquals(1, result.get(ChronoField.DAY_OF_MONTH));
            assertEquals(2, result.get(ChronoField.MONTH_OF_YEAR));
            assertEquals(2023, result.get(ChronoField.YEAR));
            assertEquals(12, result.get(ChronoField.HOUR_OF_DAY));
            assertEquals(22, result.get(ChronoField.MINUTE_OF_HOUR));
            assertEquals(true, result instanceof LocalDateTime);
        } catch (DukeException e) {
            assertNotEquals("Please use the format \"DD-MM-YYYY [HHMM]\"", e.getMessage());
            fail();
        }
    }
}
