package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        String expected = "[D] [ ] Test Deadline (by: Dec 31 2023 11:59 PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineToFileString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        String expected = "DEADLINE | 0 | Test Deadline | 2023-12-31 2359";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void testMarkAsCompleted() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
        Deadline deadline = new Deadline("Test Deadline", dateTime);

        assertFalse(deadline.isCompleted());
        deadline.setCompleted();
        assertTrue(deadline.isCompleted());
    }
}
