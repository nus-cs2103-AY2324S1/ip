package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Event;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class EventTest {
    @Test
    public void testEventIsDone() {
        Event event = new Event("Conference", LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        assertFalse(event.isDone(), "A new Event should not be marked as done");

        event.markAsDone();
        assertTrue(event.isDone(), "markAsDone() should set the Event as done");
    }
}
