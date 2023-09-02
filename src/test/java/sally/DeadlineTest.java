package sally;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

public class DeadlineTest {
    @Test
    public void validDeadlineString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 30, 14, 0);
        Deadline deadline = new Deadline("Submit report", dateTime);
        String expectedOutput = "[D][ ] Submit report (by: Sep 30 2023, 2:00 PM)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void completedDeadlineString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 30, 14, 0);
        Deadline deadline = new Deadline("Submit report", dateTime);
        deadline.mark();
        String expectedOutput = "[D][X] Submit report (by: Sep 30 2023, 2:00 PM)";
        assertEquals(expectedOutput, deadline.toString());
    }

    @Test
    public void validInputConstructor() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 30, 14, 0);
        Deadline deadline = new Deadline("Complete assignment", dateTime);
        assertNotNull(deadline);
        assertEquals("Complete assignment", deadline.task);
        assertEquals(dateTime, deadline.by);
        assertTrue(deadline.toBeDone);
    }

}
