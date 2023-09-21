package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;

import duke.task.Event;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class EventTest {

    @Test
    public void testToString() {
        LocalDateTime fromDateTime = LocalDateTime.of(2023, Month.AUGUST, 30, 16, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, Month.AUGUST, 30, 18, 0);
        Event event = new Event("Meeting", fromDateTime, toDateTime);

        String expected = "[E][ ] Meeting (from: August 30 2023 2PM to: August 30 2023 4PM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToFileString() {
        LocalDateTime fromDateTime = LocalDateTime.of(2023, Month.AUGUST, 30, 14, 0);
        LocalDateTime toDateTime = LocalDateTime.of(2023, Month.AUGUST, 30, 16, 0);
        Event event = new Event("Meeting", fromDateTime, toDateTime);

        String expected = "E | 0 | Meeting | August 30 2023 2PM - August 30 2023 4PM\n";
        assertEquals(expected, event.toFileString());
    }

    @Test
    public void testEventIsDone() {
        Event event = new Event("Conference", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertFalse(event.isDone(), "A new Event should not be marked as done");

        event.markAsDone();
        assertTrue(event.isDone(), "markAsDone() should set the Event as done");
    }
}
