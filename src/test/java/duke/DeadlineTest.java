package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 18, 0);
        Deadline deadline = new Deadline("Complete assignment", dateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        String formattedDateTime = dateTime.format(formatter);

        String expected = "[D][ ] Complete assignment (by: " + formattedDateTime + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToFileString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 18, 0);
        Deadline deadline = new Deadline("Complete assignment", dateTime);

        String expected = "D | 0 | Complete assignment | 01 Dec 2023 6:00pm\n";
        assertEquals(expected, deadline.toFileString());
    }

    @Test
    public void testDeadlineIsDone() {
        Deadline deadline = new Deadline("Submit report", LocalDateTime.now());
        assertFalse(deadline.isDone(), "A new Deadline should not be marked as done");

        deadline.markAsDone();
        assertTrue(deadline.isDone(), "markAsDone() should set the Deadline as done");
    }
}
