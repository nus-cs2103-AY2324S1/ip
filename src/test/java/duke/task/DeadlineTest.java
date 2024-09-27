package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The DeadlineTest class contains JUnit tests for the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the toFileString() function of the Deadline task to ensure it returns the correct formatted string.
     */
    @Test
    public void testToFileString() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-09-30T14:00");
        Deadline task = new Deadline("Submit report", dateTime, true);
        assertEquals("D | 1 | Submit report | 2023-09-30T14:00", task.toFileString());
    }

    /**
     * Tests the toString() function of the Deadline task to ensure it returns the correct formatted string.
     */
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.parse("2023-09-30T14:00");
        Deadline task = new Deadline("Submit report", dateTime);
        assertEquals("[D][ ] Submit report (by: Sep 30 2023 14:00)", task.toString());
    }
}
