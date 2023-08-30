package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
public class DeadlineTest {

    @Test
    public void testToString_shouldDisplayCorrectMessage() {
        LocalDateTime sampleDateTime = LocalDateTime.of(2023, 8, 30, 15, 0); // Example date and time
        Deadline deadline = new Deadline("Sample Deadline", sampleDateTime);
        String expectedToString = "[D][ ] Sample Deadline (by: 30-08-2023 15:00)";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    public void testToString_shouldDisplayCorrectMessage2() {
        LocalDateTime sampleDateTime = LocalDateTime.of(2023, 9, 30, 15, 0); // Example date and time
        Deadline deadline = new Deadline("Sample Deadline", sampleDateTime);
        String expectedToString = "[D][ ] Sample Deadline (by: 30-09-2023 15:00)";
        assertEquals(expectedToString, deadline.toString());
    }

    @Test
    public void testMarkAsDone_shouldDisplayCorrectMessage() {
        LocalDateTime sampleDateTime = LocalDateTime.of(2023, 9, 30, 15, 0); // Example date and time
        Deadline deadline = new Deadline("Sample Deadline", sampleDateTime);
        assertFalse(deadline.isDone);
        deadline.markAsDone();
        assertTrue(deadline.isDone);
    }

    @Test
    public void testMarkAsUndone_shouldDisplayCorrectMessage() {
        LocalDateTime sampleDateTime = LocalDateTime.of(2023, 9, 30, 15, 0); // Example date and time
        Deadline deadline = new Deadline("Sample Deadline", sampleDateTime);
        deadline.markAsDone();
        assertTrue(deadline.isDone);
        deadline.markAsUndone();
        assertFalse(deadline.isDone);
    }

    @Test
    public void testGetStatusIcon() {
        LocalDateTime sampleDateTime = LocalDateTime.of(2023, 9, 30, 15, 0); // Example date and time
        Deadline deadline = new Deadline("Sample Deadline", sampleDateTime);
        assertEquals(" ", deadline.getStatusIcon());
        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());
    }
}
