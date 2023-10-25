package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testConstructorAndGetters() {
        LocalDateTime from = LocalDateTime.of(2023, 8, 31, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 8, 31, 12, 0);

        Event event = new Event("Meeting", from, to, false, new ArrayList<>());
        assertEquals("Meeting", event.getDescription());
        assertFalse(event.getIsDone());
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }

    @Test
    public void testToString() {
        LocalDateTime from = LocalDateTime.of(2023, 8, 31, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 8, 31, 12, 0);

        Event event = new Event("Meeting", from, to, false, new ArrayList<>());
        assertEquals("[E][ ] Meeting (from: Aug 31 2023, 10:00 AM to: Aug 31 2023, 12:00 PM)", event.toString());

        Event completedEvent = new Event("Conference", from, to, true, new ArrayList<>());
        assertEquals("[E][X] Conference (from: Aug 31 2023, 10:00 AM to: Aug 31 2023, 12:00 PM)",
                completedEvent.toString());
    }

    @Test
    public void testWrite() {
        LocalDateTime from = LocalDateTime.of(2023, 8, 31, 10, 0);
        LocalDateTime to = LocalDateTime.of(2023, 8, 31, 12, 0);

        Event event = new Event("Meeting", from, to, false, new ArrayList<>());
        assertEquals("E | 0 | Meeting | 31/8/2023 1000 | 31/8/2023 1200", event.write());

        Event completedEvent = new Event("Conference", from, to, true, new ArrayList<>());
        assertEquals("E | 1 | Conference | 31/8/2023 1000 | 31/8/2023 1200", completedEvent.write());
    }
}
