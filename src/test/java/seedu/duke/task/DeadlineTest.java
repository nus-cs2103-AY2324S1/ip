package seedu.duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testString() {
        Deadline task = new Deadline("test", "2024-12-01 1532");
        assertEquals(task.toString(), "[D][ ] test (by: Dec 1 2024 3:32PM)");
    }

    @Test
    void testInvalidDeadline() {
        try {
            new Deadline("test", "1234");
        } catch (Exception e) {
            assertEquals("Please use the following formats:\n"
                    + "deadline task /by yyyy-mm-dd hhmm\n"
                    + "deadline task /by dd/mm/yyyy hhmm", e.getMessage());
        }
    }
}