package duke.tasks;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    public void testConstructorAndGetters() {
        LocalDateTime by = LocalDateTime.of(2023, 9, 1, 14, 0);

        Deadline deadline = new Deadline("Submit report", by);
        assertEquals("Submit report", deadline.getDescription());
        assertFalse(deadline.getIsDone());
        assertEquals(by, deadline.getBy());
    }

    @Test
    public void testToString() {
        LocalDateTime by = LocalDateTime.of(2023, 9, 1, 14, 0);


        Deadline deadline = new Deadline("Submit report", by);
        assertEquals("[D][ ] Submit report (by: Sep 1 2023, 2:00 PM)", deadline.toString());

        Deadline completedDeadline = new Deadline("Finish project", by, true);
        assertEquals("[D][X] Finish project (by: Sep 1 2023, 2:00 PM)", completedDeadline.toString());

        LocalDateTime wrongTime = LocalDateTime.of(2023, 9, 1, 14, 0);
    }

    @Test
    public void testWrite() {
        LocalDateTime by = LocalDateTime.of(2023, 9, 1, 14, 0);

        Deadline deadline = new Deadline("Submit report", by);
        assertEquals("D | 0 | Submit report | 1/9/2023 1400", deadline.write());

        Deadline completedDeadline = new Deadline("Finish project", by, true);
        assertEquals("D | 1 | Finish project | 1/9/2023 1400", completedDeadline.write());
    }
}
