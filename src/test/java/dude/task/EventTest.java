package dude.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void createDeadlineTest() {
        String description = "Tutorial";
        LocalDateTime from = LocalDateTime.of(2023, 9, 11, 14, 0);
        LocalDateTime to = LocalDateTime.of(2023, 9, 11, 15, 0);
        Event event = new Event(description, from, to);
        String expected = "[E][ ] Tutorial (from: 11 Sep 2023 02:00 PM to: 11 Sep 2023 03:00 PM)";
        assertEquals(expected, event.toString());
    }
}
