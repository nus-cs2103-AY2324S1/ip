package duke;

import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeTest {

    @Test
    public void testConstructor_WithOnlyDate() {
        DateTime dateTime = new DateTime("2023/09/01");
        assertEquals("09-1-2023", dateTime.getFormattedDate());
        assertEquals(null, dateTime.getFormattedTime());
    }

    @Test
    public void testConstructor_WithDateAndTime() {
        DateTime dateTime = new DateTime("2023/09/01 1630");
        assertEquals("09-1-2023", dateTime.getFormattedDate());
        assertEquals("16:30", dateTime.getFormattedTime());
    }

    @Test
    public void testConstructor_WithInvalidFormat_ShouldThrowException() {
        assertThrows(DateTimeParseException.class, () -> new DateTime("2023.09.01"));
        assertThrows(DateTimeParseException.class, () -> new DateTime("01/09/2023"));
        assertThrows(DateTimeParseException.class, () -> new DateTime("2023/09/01 2500"));
        assertThrows(DateTimeParseException.class, () -> new DateTime("2023/09/01 1630 1830"));
    }

    @Test
    public void testToString_WithOnlyDate() {
        DateTime dateTime = new DateTime("2023/09/01");
        assertEquals("09-1-2023", dateTime.toString());
    }

    @Test
    public void testToString_WithDateAndTime() {
        DateTime dateTime = new DateTime("2023/09/01 1630");
        assertEquals("09-1-2023 16:30", dateTime.toString());
    }
}
