package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;
public class EventTest {
    @Test
    public void toFileStringTest() {
        try {
            Event event = new Event("test", "16/10/2001 1200", "20/02/2003 1200");
            assertEquals(event.toFileString(),
                    "E | 0 | test | 16/10/2001 1200 | 20/2/2003 1200",
                    "'toFileString()' should return a simplified string to be saved to file");
        } catch (DukeException e) {
            // do nothing
        }
    }

    @Test
    public void toStringTest() {
        try {
            Event event = new Event("test", "16/10/2001 1200", "20/02/2003 1200");
            assertEquals(event.toString(),
                    "[E][ ] test (from: Oct 16 2001 12:00 to: Feb 20 2003 12:00)",
                    "'toString()' should return a string to be printed");
        } catch (DukeException e) {
            // do nothing
        }
    }
}