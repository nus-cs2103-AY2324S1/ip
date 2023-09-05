package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        String expected = "[D][ ] Complete assignment (by: Sep 30 2023)";
        assertEquals(expected, task.toString());
    }

    @Test
    void testMark() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        task.mark();
        assertTrue(task.done);
    }

    @Test
    void testUnmark() {
        LocalDate deadline = LocalDate.of(2023, 9, 30);
        Deadline task = new Deadline("Complete assignment", deadline);
        task.mark();
        assertTrue(task.done);
        task.unMark();
        assertFalse(task.done);
    }
}
