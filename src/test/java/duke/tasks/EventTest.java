package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    public void constructor_newEvent_hasCorrectTimings() {
        LocalDateTime fromTime = LocalDateTime.of(2023, 9, 20, 9, 0);
        LocalDateTime toTime = LocalDateTime.of(2023, 9, 20, 10, 0);
        Event event = new Event("Test Event", fromTime, toTime);

        assertNotNull(event);
        assertFalse(event.isDone());
        assertEquals("Test Event", event.getTaskName());
        assertEquals(fromTime, event.fromTime);
        assertEquals(toTime, event.toTime);
    }

    @Test
    public void toString_validInput_returnsCorrectString() {
        LocalDateTime fromTime = LocalDateTime.of(2023, 9, 20, 9, 0);
        LocalDateTime toTime = LocalDateTime.of(2023, 9, 20, 10, 0);
        Event event = new Event("Test Event", fromTime, toTime);

        String expected = "[E][ ] Test Event (from: 20 Sep 2023, 09:00 to: 20 Sep 2023, 10:00)";
        assertEquals(expected, event.toString());
    }

}