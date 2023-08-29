package sally;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sally.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import sally.SallyException;

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
