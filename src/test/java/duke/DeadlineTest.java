package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

// Solution adapted and inspired from https://chat.openai.com/share/a8b02fc2-b4f0-490d-ad3e-a3e06fe3a168
public class DeadlineTest {
    @Test
    public void testDeadlineIsDone() {
        Deadline deadline = new Deadline("Submit report", LocalDateTime.now());
        assertFalse(deadline.isDone(), "A new Deadline should not be marked as done");

        deadline.markAsDone();
        assertTrue(deadline.isDone(), "markAsDone() should set the Deadline as done");
    }
}
