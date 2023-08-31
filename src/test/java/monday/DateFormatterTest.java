package monday;

import monday.monday.dateTime.DateFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.LocalDateTime;

/**
 * The DateFormatterTest class is responsible for testing the functionality of the DateFormatter class.
 */
public class DateFormatterTest {
    /**
     * Tests the DateTimeParseException scenario.
     */
    @Test
    public void testDateTimeParseException() {
        try {
            DateFormatter.parseTime("31-08-2023", "yyyy-MM-dd HH:mm");
            fail("Expected IllegalArgumentException to be thrown.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid date format. Please use the format: yyyy-MM-dd HH:mm", e.getMessage());
        }
    }

    /**
     * Tests the parseTime method.
     */
    @Test
    public void testParseTime(){
        String dateTimeString = "2023-08-31 12:34";
        String pattern = "yyyy-MM-dd HH:mm";
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 8, 31, 12, 34);
        LocalDateTime parsedDateTime = DateFormatter.parseTime(dateTimeString, pattern);
        assertEquals(expectedDateTime, parsedDateTime);
    }

    /**
     * Tests the format method with a valid DateTime object.
     */
    @Test
    public void testFormat_ValidDateTime() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 31, 12, 34);
        String pattern = "yyyy-MM-dd HH:mm";
        String expectedFormattedString = "2023-08-31 12:34";
        String formattedString = DateFormatter.format(dateTime, pattern);
        assertEquals(expectedFormattedString, formattedString);
    }
}