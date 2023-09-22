package avalon;

import avalon.utility.DateTimeParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class DateTimeParserTest {

    @Test
    public void testStringToDateTime() {
        // Test parsing a valid date-time string
        String validDateTimeString = "2023-09-01 1800";
        LocalDateTime dateTime = DateTimeParser.stringToDateTime(validDateTimeString);
        assertNotNull(dateTime);
        assertEquals(2023, dateTime.getYear());
        assertEquals(9, dateTime.getMonthValue());
        assertEquals(1, dateTime.getDayOfMonth());
        assertEquals(18, dateTime.getHour());
        assertEquals(0, dateTime.getMinute());

        // Test parsing an invalid date-time string
        String invalidDateTimeString = "2023-09-01 18:00 PM"; // Invalid format
        LocalDateTime invalidDateTime = DateTimeParser.stringToDateTime(invalidDateTimeString);
        assertEquals(null, invalidDateTime);
    }

    @Test
    public void testDateTimeToString() {
        // Test formatting a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 1, 18, 0);
        String formattedDateTimeString = DateTimeParser.dateTimeToString(dateTime);
        assertEquals("2023-09-01 1800", formattedDateTimeString);
    }

    @Test
    public void testPrintDateTimeToString() {
        // Test formatting a LocalDateTime object for human-readable output
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 1, 18, 0);
        String formattedDateTimeString = DateTimeParser.printDateTimeToString(dateTime);
        assertEquals("01 Sep 2023 18:00", formattedDateTimeString);
    }
}
