package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testGetSaveFormat() {
        Event event = new Event("2101 Tutorial", "2023-08-29 08:00", "2023-08-29 10:00");
        assertEquals("EVENT||0||2101 Tutorial /from 2023-08-29 08:00 /to 2023-08-29 10:00\n", event.getSaveFormat());
    }

    @Test
    public void testToString() {
        Event event = new Event("2101 Tutorial", "2023-08-29 08:00", "2023-08-29 10:00");
        assertEquals("[E][ ] 2101 Tutorial (from: Tue 29 Aug 2023 08:00 to: Tue 29 Aug 2023 10:00)", event.toString());
    }
}
