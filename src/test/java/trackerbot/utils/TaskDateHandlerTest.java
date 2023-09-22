package trackerbot.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import org.junit.jupiter.api.Test;

import trackerbot.exception.TrackerBotException;

/**
 * Test file for TaskDateHandlerTest. <br>
 * This parses user input to Date. As the parse can be variable with optional
 * fields, we test to see if the parser handles bad input properly.
 */
public class TaskDateHandlerTest {
    @Test
    public void convertInputToDate_dayAndMonthInput_shouldParseProperly() throws TrackerBotException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("1/12");
        String expected = LocalDateTime.now().get(ChronoField.YEAR_OF_ERA) + "-12-01T00:00";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_dayMonthYearInput_shouldParseProperly() throws TrackerBotException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("24/1/2012");
        String expected = "2012-01-24T00:00";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_dayMonthTimeInput_shouldParseProperly() throws TrackerBotException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("09/08 0654");
        String expected = LocalDateTime.now().get(ChronoField.YEAR_OF_ERA) + "-08-09T06:54";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_fullInput_shouldParseProperly() throws TrackerBotException {
        LocalDateTime date = TaskDateHandler.convertInputToDate("20/9/1922 2059");
        String expected = "1922-09-20T20:59";
        assertEquals(expected, date.toString());
    }

    @Test
    public void convertInputToDate_emptyInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Error in parsing input to date: "
                    + "Text '' could not be parsed at index 0"
                    + "\nAdditional Date Fields should be in the format DD/MM(/YYYY)( HHmm)", e.getMessage());
        }
    }

    @Test
    public void convertInputToDate_faultyInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("21//2 2359");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Error in parsing input to date: "
                    + "Text '21//2 2359' could not be parsed at index 3"
                    + "\nAdditional Date Fields should be in the format DD/MM(/YYYY)( HHmm)", e.getMessage());
        }
    }

    @Test
    public void convertInputToDate_notFullYearInput_shouldThrow() {
        try {
            LocalDateTime date = TaskDateHandler.convertInputToDate("21/2/20 2359");
            fail();
        } catch (TrackerBotException e) {
            assertEquals("Error in parsing input to date: "
                    + "Text '21/2/20 2359' could not be parsed, unparsed text found at index 4"
                    + "\nAdditional Date Fields should be in the format DD/MM(/YYYY)( HHmm)", e.getMessage());
        }
    }
}
