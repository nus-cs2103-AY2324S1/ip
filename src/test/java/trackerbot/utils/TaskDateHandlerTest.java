package trackerbot.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test file for TaskDateHandlerTest. <br>
 * This parses user input to Date. As the parse can be variable with optional
 * fields, we test to see if the parser handles bad input properly.
 */
public class TaskDateHandlerTest {
    @Test
    public void convertInputToDate_dayAndMonthInput_shouldParseProperly() throws DateTimeParseException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("1/12");
        String expected = LocalDateTime.now().get(ChronoField.YEAR_OF_ERA) + "-12-01T00:00";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_dayMonthYearInput_shouldParseProperly() throws DateTimeParseException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("24/1/2012");
        String expected = "2012-01-24T00:00";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_dayMonthTimeInput_shouldParseProperly() throws DateTimeParseException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("09/08 0654");
        String expected = LocalDateTime.now().get(ChronoField.YEAR_OF_ERA) + "-08-09T06:54";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_fullInput_shouldParseProperly() {
        LocalDateTime date = TaskDateHandler.convertInputToDate("20/9/1922 2059");
        String expected = "1922-09-20T20:59";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_emptyInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '' could not be parsed at index 0", e.getMessage());
        }
    }

    @Test
    public void convertInputToDate_faultyInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("21//2 2359");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '21//2 2359' could not be parsed at index 3", e.getMessage());
        }
    }

    @Test
    public void convertInputToDate_notFullYearInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("21/2/20 2359");
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '21/2/20 2359' could not be parsed, unparsed text found at index 4", e.getMessage());
        }
    }
}
