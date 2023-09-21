package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class DeadlineTest {
    @Test
    public void testToString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 18, 0);
        Deadline deadline = new Deadline("Complete badminton training", dateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        String formattedDateTime = dateTime.format(formatter);

        String expected = "[D][ ] Complete badminton training (by: " + formattedDateTime + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToString_markAsDone() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 12, 1, 18, 0);
        Deadline deadline = new Deadline("Complete badminton training", dateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mma");
        String formattedDateTime = dateTime.format(formatter);

        deadline.markAsDone();

        String expected = "[D][X] Complete badminton training (by: " + formattedDateTime + ")";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testDeadlineToFileString_markAsDone() {
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 24, 23, 59);
        Deadline deadline = new Deadline("submit transcript to ben", dateTime);
        deadline.markAsDone();
        String expected = "D | 1 | submit transcript to ben | 24 Sep 2023 11:59PM\n";
        String actual = deadline.toFileString();
        assertEquals(expected.toLowerCase(), actual.toLowerCase());
    }

    @Test
    public void testDeadlineToFileString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.SEPTEMBER, 24, 23, 59);
        Deadline deadline = new Deadline("submit transcript to ben", dateTime);

        String expected = "D | 0 | submit transcript to ben | 24 Sep 2023 11:59PM\n";
        String actual = deadline.toFileString();
        assertEquals(expected.toLowerCase(), actual.toLowerCase());
    }

    @Test
    public void testDeadlineIsDone() {
        Deadline deadline = new Deadline("Submit report", LocalDateTime.now());
        assertFalse(deadline.isDone(), "A new Deadline should not be marked as done");

        deadline.markAsDone();
        assertTrue(deadline.isDone(), "markAsDone() should set the Deadline as done");
    }
}
