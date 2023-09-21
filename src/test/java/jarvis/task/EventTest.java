package jarvis.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToString() {
        Event event = new Event("Meeting", LocalDateTime.of(2023, 9, 20, 10, 30),
                LocalDateTime.of(2023, 9, 20, 11, 30));
        assertEquals("Meeting", event.getDescription());
        assertEquals("[E][ ] Meeting (from: Sep 20 2023 1030 to: Sep 20 2023 1130)", event.toString());
    }

    @Test
    public void testToStringCompleted() {
        Event event = new Event("Conference", true,
                LocalDateTime.of(2023, 10, 1, 9, 0),
                LocalDateTime.of(2023, 10, 1, 17, 0));
        assertEquals("[E][X] Conference (from: Oct 1 2023 0900 to: Oct 1 2023 1700)", event.toString());
    }

    @Test
    public void testToSaveString() {
        Event event = new Event("Dinner", LocalDateTime.of(2023, 11, 25, 19, 0),
                LocalDateTime.of(2023, 11, 25, 21, 0));
        assertEquals("E | 0 | Dinner | Nov 25 2023 1900 | Nov 25 2023 2100", event.toSaveString());
    }
}