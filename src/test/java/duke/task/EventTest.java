package duke.task;

import duke.task.Event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventStringConversion() {
        assertEquals(
                "[E][ ] Career Fair (from: 30 Aug 12pm to: 5pm)",
                new Event("Career Fair", "30 Aug 12pm", "5pm").toString()
        );
    }
}
