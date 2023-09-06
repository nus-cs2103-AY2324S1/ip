package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("event description", LocalDate.EPOCH, LocalTime.NOON,
                LocalDate.EPOCH, LocalTime.MIDNIGHT);
        String eString = event.toString();
        assertEquals("[E][ ] event description (from: 1970-01-01 12:00 to: 1970-01-01 00:00)",
                eString);
    }
}
