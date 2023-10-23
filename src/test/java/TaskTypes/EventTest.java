package TaskTypes;
import URBOI_PACKIN.TaskTypes.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class EventTest {
    private Event event;

    @BeforeEach
    public void setUp() {
        // Initialize an Event object before each test
        event = new Event("Sample Event", "2023-10-23 1400", "2023-10-23 1600");
    }

    @Test
    public void testEventConstructor() {
        // Check if the Event constructor sets the description, from, and to correctly
        assertEquals("Sample Event", event.getDescription());
        assertEquals("2023-10-23 1400", event.getFrom());
        assertEquals("2023-10-23 1600", event.getTo());
    }

    @Test
    public void testEventToFileString() {
        // Check the toFileString method for an Event task
        assertEquals("E | 0 | Sample Event | 2023-10-23 1400 | 2023-10-23 1600", event.toFileString());
    }

    @Test
    public void testEventToString() {
        // Check the toString method for an Event task
        assertEquals("[E][ ] Sample Event (from: 2023-10-23 1400 to: 2023-10-23 1600)", event.toString());
    }
}
