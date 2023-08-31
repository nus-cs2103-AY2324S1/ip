package duke;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline newDeadline = new Deadline("submit report", formatDateTime("21/9/2023 1000"));
        assertEquals("[D][ ] submit report (by: Sep 21 2023 10:00)", newDeadline.toString());

        Deadline done = new Deadline("finish lab", formatDateTime("11/9/2023 2200"));
        done.markAsDone();
        assertEquals("[D][X] finish lab (by: Sep 11 2023 22:00)", done.toString());

    }
    
    @Test
    public void testAsMark() {
        Deadline deadline = new Deadline("buy lunch", formatDateTime("3/9/2023 1300"));
        assertFalse(deadline.isDone());
        deadline.markAsDone();
        assertTrue(deadline.isDone());
    }

    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
