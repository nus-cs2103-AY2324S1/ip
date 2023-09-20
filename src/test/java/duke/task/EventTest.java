package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    /**
     * Tests the toFileString() function of the Event task to ensure it returns the correct formatted string.
     */
    @Test
    public void testToFileString() {
        LocalDateTime startDateTime = LocalDateTime.parse("2023-09-30T14:00");
        LocalDateTime endDateTime = LocalDateTime.parse("2023-09-30T16:00");
        Event task = new Event("Team meeting", startDateTime, endDateTime, true);
        assertEquals("E | 1 | Team meeting | 2023-09-30T14:00 | 2023-09-30T16:00", task.toFileString());
    }

    /**
     * Tests the toString() function of the Event task to ensure it returns the correct formatted string.
     */
    @Test
    public void testToString() {
        LocalDateTime startDateTime = LocalDateTime.parse("2023-09-30T14:00");
        LocalDateTime endDateTime = LocalDateTime.parse("2023-09-30T16:00");
        Event task = new Event("Team meeting", startDateTime, endDateTime);
        assertEquals("[E][ ] Team meeting (from: Sep 30 2023 14:00 to: Sep 30 2023 16:00)", task.toString());
    }
}
