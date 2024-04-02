package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {


    @Test
    public void testEventToString_notDone_success() {
        Event event = new Event("Team Meeting", "2022-12-01 1400", "2022-12-01 1600");
        String expectedOutput = "[E][ ] Team Meeting (from: Dec 01 2022 1400 to: Dec 01 2022 1600)";
        assertEquals(expectedOutput, event.toString());
    }

    @Test
    public void testMarkAsDone_success() {
        Event event = new Event("Team Meeting", "2022-12-01 1400", "2022-12-01 1600");
        event.markAsDone();
        assertTrue(event.isDone);
        assertEquals("[E][X] Team Meeting (from: Dec 01 2022 1400 to: Dec 01 2022 1600)", event.toString());
    }

    @Test
    public void testEventToFile_notDone_success() {
        Event event = new Event("Team Meeting", "2022-12-01 1400", "2022-12-01 1600");
        String expectedOutput = "E | 0 | Team Meeting | 2022-12-01T14:00 | 2022-12-01T16:00";
        assertEquals(expectedOutput, event.toFile());
    }

    @Test
    public void testParseDateTime_alternateFormat_success() {
        Event event = new Event("Team Meeting", "1/12/2022 1400", "1/12/2022 1600");
        String expectedOutput = "[E][ ] Team Meeting (from: Dec 01 2022 1400 to: Dec 01 2022 1600)";
        assertEquals(expectedOutput, event.toString());
    }
}
