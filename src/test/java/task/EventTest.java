package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testEventCreation() {
        LocalDateTime from = LocalDateTime.of(2023, 12, 31, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 12, 31, 16, 0);
        Event event = new Event("Test Event", from, to);

        assertEquals("Test Event", event.getDescription());
        assertFalse(event.isCompleted());
        assertEquals(TaskType.EVENT, event.getTaskType());
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }

    @Test
    public void testEventFromFileString() {
        String fileString = "EVENT | 1 | Test Event | 2023-12-31 1400 | 2023-12-31 1600";
        Event event = Event.fromFileString(fileString);

        assertEquals("Test Event", event.getDescription());
        assertTrue(event.isCompleted());
        assertEquals(TaskType.EVENT, event.getTaskType());

        LocalDateTime expectedFrom = LocalDateTime.of(2023, 12, 31, 14, 0);
        LocalDateTime expectedTo = LocalDateTime.of(2023, 12, 31, 16, 0);
        assertEquals(expectedFrom, event.getFrom());
        assertEquals(expectedTo, event.getTo());
    }

    @Test
    public void testEventToString() {
        LocalDateTime from = LocalDateTime.of(2023, 12, 31, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 12, 31, 16, 0);
        Event event = new Event("Test Event", from, to);

        String expected = "[E] [ ] Test Event (from: Dec 31 2023 02:00 PM to: Dec 31 2023 04:00 PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventToFileString() {
        LocalDateTime from = LocalDateTime.of(2023, 12, 31, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 12, 31, 16, 0);
        Event event = new Event("Test Event", from, to);

        String expected = "EVENT | 0 | Test Event | 2023-12-31 1400 | 2023-12-31 1600";
        assertEquals(expected, event.toFileString());
    }
}
