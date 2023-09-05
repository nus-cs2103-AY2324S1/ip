package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class EventTest {
    @Test
    public void event_validCommand_success() {
        try {
            Task event = new Event("Birthday Party", "2023-09-01", "2023-09-02");
            String expectedOutcome = "[E][ ] Birthday Party (from: Sep 01 2023 to: Sep 02 2023)";
            assertEquals(expectedOutcome, event.toString());
        } catch (DukeException e) {
            fail("This should not be possible! It should have worked properly.");
        }
    }
    @Test
    public void event_invalidCommand_throwException() {
        try {
            Task event = new Event("Birthday Party", "2023-08-31", "2023-08-32");
            fail("This should not be possible! It should have thrown a DukeException");
        } catch (DukeException e) {
            assertEquals("Invalid date format! Please input in the form of yyyy-MM-dd", e.getMessage());
        }
    }
}
