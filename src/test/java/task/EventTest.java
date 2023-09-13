package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testToString() {
        Event event = new Event("event description", "1970-01-01 12:00", "1970-01-01 00:00");
        String eString = event.toString();
        assertEquals("[E][ ] event description (from: 1970-01-01 12:00 to: 1970-01-01 00:00)",
                eString);
    }
}
