package duke;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateTimeTest {

    @Test
    public void testDateTime_withOnlyDate() {
        DateTime dt = new DateTime("2023/09/21");
        assertEquals("09-21-2023", dt.getFormattedDate());
        assertEquals(null, dt.getFormattedTime());
    }

    @Test
    public void testDateTime_withDateAndTime() {
        DateTime dt = new DateTime("2023/09/21 1430");
        assertEquals("09-21-2023", dt.getFormattedDate());
        assertEquals("14:30", dt.getFormattedTime());
    }

    @Test
    public void testDateTime_withInvalidDateFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            new DateTime("2023.09.21");
        });
    }

    @Test
    public void testDateTime_withInvalidTimeFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            new DateTime("2023/09/21 14:65");
        });
    }

    @Test
    public void testToString_withOnlyDate() {
        DateTime dt = new DateTime("2023/09/21");
        assertEquals("09-21-2023", dt.toString());
    }

    @Test
    public void testToString_withDateAndTime() {
        DateTime dt = new DateTime("2023/09/21 1430");
        assertEquals("09-21-2023 14:30", dt.toString());
    }

}